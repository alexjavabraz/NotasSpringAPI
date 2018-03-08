package notas.api.spring;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import notas.api.domain.Conta;
import notas.api.domain.Transacao;
import notas.api.domain.Usuario;
import notas.api.dto.TransacaoDTO;
import notas.api.spring.repo.ContaRepository;
import notas.api.spring.repo.TransacaoRepository;
import notas.api.spring.repo.UsuarioRepository;

/**
 * 
 * @author alex.braz
 *
 */
@RestController
public class IndexController {
 
	@SuppressWarnings("unused")
	private final UsuarioRepository usuarioRepo;
	
	@SuppressWarnings("unused")
	private final TransacaoRepository transacaoRepo;
	
	@SuppressWarnings("unused")
	private final ContaRepository contaRepo;
	
	private static final String RETORNO_ERRO = "Erro valores inválidos";
	private static final String RETORNO_SUCESSO = "Sucesso";
	

	@Autowired
	IndexController(UsuarioRepository usuarioRepo, TransacaoRepository transacaoRepo, ContaRepository contaRepo) {
		this.usuarioRepo   = usuarioRepo;
		this.transacaoRepo = transacaoRepo;
		this.contaRepo     = contaRepo;
	}
	
	@RequestMapping("/")
	public ModelAndView inicio(Model model, Principal principal) {
		return index();
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		System.out.println("Executou... /login");
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		System.out.println("Executou... /register");
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		System.out.println("Executou... /index");
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("/home")
	public ModelAndView home() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		System.out.println("Executou... /home " + currentPrincipalName);
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SALDO, method = RequestMethod.GET)
	public String consultarSaldo(@PathVariable("id") String sconta){
		Conta conta = contaRepo.findById(Long.parseLong(sconta));
		
		String retorno = "";
		
		try{
			if(conta!=null && conta.getSaldo() != null){
				BigDecimal bd = conta.getSaldo();
				
				DecimalFormatSymbols symbol = new DecimalFormatSymbols();
				symbol.setDecimalSeparator(' ');
				symbol.setGroupingSeparator(' ');
				
				DecimalFormat df = new DecimalFormat("#,###", symbol);
			    retorno = df.format(bd);
			}
		}catch(Exception e){
			retorno = "0";
		}
		
		
		return retorno;
	}
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.SAQUE, method = RequestMethod.POST)
	public TransacaoDTO saque(@RequestBody TransacaoDTO simpleTransacao){
		
		TransacaoDTO retorno = new TransacaoDTO();
		
		if(simpleTransacao == null || simpleTransacao.getValor() == null || simpleTransacao.getValor().doubleValue() <= 0){
			retorno.setMensagem(RETORNO_ERRO);
			return retorno;
		}
		
		/**
		 * Não permitir casa decimal
		 */
		if(simpleTransacao.getValor() % 1 != 0) {
			retorno.setMensagem(RETORNO_ERRO);
			return retorno;
		}
		
		try{
			Conta origem    = contaRepo.findById(Long.parseLong(simpleTransacao.getOrigem()));
			
			if(origem == null) {
				retorno.setMensagem(RETORNO_ERRO);
				return retorno;
			}
			
			Transacao transacao = new Transacao();
			transacao.setDataTransacao(new Date());
			transacao.setOrigem(origem);
			transacao.setVlrValor(new BigDecimal(simpleTransacao.getValor()));
			origem.setSaldo(origem.getSaldo().subtract(transacao.getVlrValor()));
			
			if(origem.getSaldo().compareTo(BigDecimal.ZERO) == -1){
				retorno.setMensagem(RETORNO_ERRO);
				return retorno;
			}
			
			/**
			 * Salva a transacao
			 */
			transacao = transacaoRepo.save(transacao);
			
			/**
			 * Salva o novo saldo da conta
			 */
			origem    = contaRepo.save(origem);
			
			
			retorno.setMensagem(RETORNO_SUCESSO);
			return retorno;
			
		}catch(Exception e){
			retorno.setMensagem(RETORNO_ERRO);
			return retorno;
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/rest/eth/test", method = RequestMethod.GET)
	public TransacaoDTO getTransacao(){
		TransacaoDTO retorno = new TransacaoDTO();
		try{
			retorno.setDataTransacao("10/10/2019");
			retorno.setId(1l);
			retorno.setOrigem("Origem");
			retorno.setValor(10d);
			return retorno;
		}catch(Exception e){
			return null;
		}
	}	
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_TODAS_TRANSACOES, method = RequestMethod.GET)
	public List<TransacaoDTO> listarTodasTransacoes(){
		List<Transacao> entidades = transacaoRepo.findAll();
		List<TransacaoDTO> retorno = new ArrayList<TransacaoDTO>();
		try{
			
			/**
			 * 
			 */
			if(entidades != null && !entidades.isEmpty()) {
				entidades.forEach((item) -> retorno.add(new TransacaoDTO(item)));
			}
			 
			return retorno;
		}catch(Exception e){
			return null;
		}
		
	}	

}
