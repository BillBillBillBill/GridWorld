import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

public class CalculatorTest {
	public CalculatorController calculator = new CalculatorController();

	@Test
	public void alwayTrue() {
		assertEquals(1, 1);
	}

	@Ignore
	@Test
	public void alwayFail() {
		assertEquals(1, 0);
	}

	@Test
	public void testSimpleCalculator() {
		Double number1 = 1.0;
		Double number2 = 2.0;
		String result = calculator.calculate(number1, number2, "+");
		assertEquals(Double.parseDouble(result), 3.0, 0.000000001);
	}

	@Test
	public void calculatorAdd() {
		Double number1 = 1.1;
		Double number2 = 2.2;
		String result = calculator.calculate(number1, number2, "+");
		assertEquals(Double.parseDouble(result), 3.3, 0.000000001);
	}

	@Test
	public void calculatorSub1() {
		Double number1 = 1.12;
		Double number2 = 2.23;
		String result = calculator.calculate(number1, number2, "-");
		assertEquals(Double.parseDouble(result), -1.11, 0.000000001);
	}

	@Test
	public void calculatorSub2() {
		Double number1 = 1.12;
		Double number2 = -2.23;
		String result = calculator.calculate(number1, number2, "-");
		assertEquals(Double.parseDouble(result), 3.35, 0.000000001);
	}

	@Test
	public void calculatorMul() {
		Double number1 = 1.1;
		Double number2 = 2.2;
		String result = calculator.calculate(number1, number2, "*");
		assertEquals(Double.parseDouble(result), 2.42, 0.000000001);
	}

	@Test
	public void calculatorDiv() {
		Double number1 = 1.1;
		Double number2 = 2.2;
		String result = calculator.calculate(number1, number2, "/");
		assertEquals(Double.parseDouble(result), 0.5, 0.000000001);
	}

}