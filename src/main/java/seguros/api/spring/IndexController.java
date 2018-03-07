package seguros.api.spring;

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

import seguros.api.domain.Conta;
import seguros.api.domain.Transacao;
import seguros.api.domain.Usuario;
import seguros.api.dto.TransacaoDTO;
import seguros.api.spring.repo.ContaRepository;
import seguros.api.spring.repo.TransacaoRepository;
import seguros.api.spring.repo.UsuarioRepository;

/**
 * 
 * @author alex.braz
 *
 */
@RestController
public class IndexController {
 
	private static final String ADMIN     = "admin";
	private static final String PRONATURA = "pronatura";
	private static final String ZSCORE    = "zscore";

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
	
	@RequestMapping("/charts")
	public ModelAndView charts() {
		System.out.println("Executou... /charts");
		ModelAndView mav = new ModelAndView("charts");
		return mav;
	}
	
	@RequestMapping("/tables")
	public ModelAndView tables() {
		System.out.println("Executou... /tables");
		ModelAndView mav = new ModelAndView("tables");
		return mav;
	}
	
	@RequestMapping("/navbar")
	public ModelAndView navbar() {
		System.out.println("Executou... /navbar");
		ModelAndView mav = new ModelAndView("navbar");
		return mav;
	}
	
	@RequestMapping("/cards")
	public ModelAndView cards() {
		System.out.println("Executou... /cards");
		ModelAndView mav = new ModelAndView("cards");
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		System.out.println("Executou... /register");
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}	
	
	@RequestMapping("/blank")
	public ModelAndView blank() {
		System.out.println("Executou... /blank");
		ModelAndView mav = new ModelAndView("blank");
		return mav;
	}
	
	
	@RequestMapping("/forgot-password")
	public ModelAndView proNatura() {
		System.out.println("Executou... /forgot-password");
		ModelAndView mav = new ModelAndView("forgot-password");
		return mav;
	}
	
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
	public String transferir(@RequestBody TransacaoDTO simpleTransacao){
		
		if(simpleTransacao == null || simpleTransacao.getValor() == null || simpleTransacao.getValor().doubleValue() <= 0){
			return RETORNO_ERRO;
		}
		
		try{
			Conta origem    = contaRepo.findById(Long.parseLong(simpleTransacao.getOrigem()));
			Usuario usuario = usuarioRepo.findById(Long.parseLong(simpleTransacao.getIdUsuario()));
			
			if(origem == null || usuario == null) {
				return RETORNO_ERRO;
			}
			
			Transacao transacao = new Transacao();
			transacao.setDataTransacao(new Date());
			transacao.setOrigem(origem);
			transacao.setUsuarioInclusao(usuario);
			transacao.setVlrValor(new BigDecimal(simpleTransacao.getValor()));
			origem.setSaldo(origem.getSaldo().subtract(transacao.getVlrValor()));
			
			if(origem.getSaldo().compareTo(BigDecimal.ZERO) == -1){
				return RETORNO_ERRO;
			}
			
			transacao = transacaoRepo.save(transacao);
			origem = contaRepo.save(origem);
			
			System.out.println("Depois de salvar Saldo origem "  + origem.getId() + " " + origem.getSaldo());
			
			return RETORNO_SUCESSO;
		}catch(Exception e){
			return RETORNO_ERRO;
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(value = ContractRestURIConstants.LISTAR_TODAS_TRANSACOES, method = RequestMethod.POST)
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
