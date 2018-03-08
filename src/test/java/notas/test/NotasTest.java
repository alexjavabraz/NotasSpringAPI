package notas.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import notas.api.dto.TransacaoDTO;
import notas.api.spring.service.TransacaoService;

public class NotasTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void contadorDeNotasVariosValores() {
		TransacaoService service = new TransacaoService();
		
		Double valor = 30d;
		TransacaoDTO t = new TransacaoDTO();
		t.setValor(valor);
		service.contarNotas(t);
		assertEquals(1, t.getQtdNotasDez());
		assertEquals(1, t.getQtdNotasVinte());

		valor = 80d;
		t = new TransacaoDTO();
		t.setValor(valor);
		service.contarNotas(t);
		assertEquals(1, t.getQtdNotasDez());
		assertEquals(1, t.getQtdNotasVinte());
		assertEquals(1, t.getQtdNotasCinquenta());

		valor = 800d;
		t = new TransacaoDTO();
		t.setValor(valor);
		service.contarNotas(t);
		assertEquals(8, t.getQtdNotasCem());

		valor = 330d;
		t = new TransacaoDTO();
		t.setValor(valor);
		service.contarNotas(t);
		assertEquals(3, t.getQtdNotasCem());
		assertEquals(1, t.getQtdNotasVinte());
		assertEquals(1, t.getQtdNotasDez());

		valor = 10d;
		t = new TransacaoDTO();
		t.setValor(valor);
		service.contarNotas(t);
		assertEquals(1, t.getQtdNotasDez());
	}

	@Test
	public void throwsIllegalArgumentExceptionSeNegativo() {
		TransacaoService service = new TransacaoService();
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Negative value not allowed");
		TransacaoDTO t = new TransacaoDTO();
		t.setValor(-1d);
		service.contarNotas(t);
	}
	
	@Test
	public void throwsIllegalArgumentExceptionSeValorInvalido() {
		TransacaoService service = new TransacaoService();
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Value not allowed");
		service.contarNotas(null);
	}


}
