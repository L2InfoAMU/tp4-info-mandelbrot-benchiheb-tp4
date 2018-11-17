package mandelbrot;

import org.junit.jupiter.api.Test;

import static mandelbrot.Complex.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final Complex zero = new Complex(0,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., zero.real, Helpers.EPSILON);
        assertEquals(2., twoI.real, Helpers.EPSILON);
        assertEquals(1., onePlusI.real, Helpers.EPSILON);
        assertEquals(-1., minusOne.imaginary, Helpers.EPSILON);
        assertEquals(2., two.imaginary, Helpers.EPSILON);
        assertEquals(0., zero.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., two.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., minusOne.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(imaginary, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., ZERO.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getImaginary());
        assertEquals(1, Complex.ONE.getReal());
    }

    @Test
    void testZero(){
        assertEquals(0, ZERO.getReal());
        assertEquals(0, ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, zero.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(imaginary,imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE);
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), two.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()-> ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, minusOne.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), zero.divide(Complex.ONE));
        assertEquals(minusI,minusOne.divide(Complex.ONE));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(ZERO, ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(-imaginary,imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(ZERO, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        //assertEquals("Complex{real=1.0, imaginary=-1.0}", minusOne.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(imaginary,real).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    public static Complex real(double real) {
        return new Complex(real, 0);
    }

    public Complex add(Complex addend) {
        return new Complex(this.real + addend.real,
                this.imaginary + addend.imaginary);
    }

    Complex subtract(Complex subtrahend) {
        return new Complex(this.real - subtrahend.real, this.imaginary - subtrahend.imaginary);
    }

    Complex multiply(Complex factor) {
        return new Complex(
                this.real * factor.real - this.imaginary * factor.imaginary,
                this.real * factor.imaginary + this.imaginary * factor.real
        );
    }

    double squaredModulus() {
        return real * real + imaginary * imaginary - 2*imaginary;
    }

    double modulus() {
        return Math.sqrt(real*real + imaginary*imaginary);
    }




}