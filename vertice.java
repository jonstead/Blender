public class vertice {
    
    public static void main(String[] args) throws Exception
    {
        double[] a = {1, 1};
        double[] b = {2, 2};
        double[] c = {1.5, 3};
        System.out.println("abc, " + verticeTurnProblemDegrees(a, b, c) + " degrees, without rounding");
        System.out.println("acb, " + verticeTurnProblemDegrees(a, c, b) + " degrees, without rounding");
        System.out.println("bca, " + verticeTurnProblemDegrees(b, c, a) + " degrees, without rounding");
        System.out.println("bac, " + verticeTurnProblemDegrees(b, a, a) + " degrees, without rounding");
        System.out.println("cab, " + verticeTurnProblemDegrees(c, a, b) + " degrees, without rounding");
        System.out.println("cba, " + verticeTurnProblemDegrees(c, b, a) + " degrees, without rounding");

        double[] aa = {5, 12};
        double[] ab = {10, 5};
        double[] ac = {4, 9};
        System.out.println(verticeTurnProblemDegrees(aa, ab, ac) + " degrees, without rounding");

    }

    static double[] verticeNormalize(double[] A)
    {
        double total = 0;
        for(int i = 0; i < A.length; i++){
            total += A[i] * A[i];
        }
        total = Math.sqrt(total);
        double[] c = new double[A.length];
        for(int i = 0; i < A.length; i++){
            c[i] = A[i] / total;
        }
        return c;
    }
    
    static double[] verticeAddition(double[] A, double[] B)
    {
        double[] c = {0};
        if(A.length != B.length){
            return c;
        }
        c = new double[A.length];
        for(int i = 0; i < A.length; i++){
            c[i] = A[i] + B[i];
        }
        return c;
    }
    
    static double[] verticeSubtraction(double[] A, double[] B)
    {
        double[] c = {0};
        if(A.length != B.length){
            return c;
        }
        c = new double[A.length];
        for(int i = 0; i < A.length; i++){
            c[i] = A[i] - B[i];
        }
        return c;
    }

    static double verticeMultiplication(double[] A, double[] B)
    {
        if(A.length != B.length){
            return 0;
        }
        double[] c = new double[A.length];
        for(int i = 0; i < A.length; i++){
            c[i] = A[i] * B[i];
            // System.out.println("A[" + i + "]: = " + A[i]);
            // System.out.println("B[" + i + "]: = " + B[i]);
            // System.out.println("C[" + i + "]: = " + c[i]);
        }
        double angle = 0;
        for(int i = 0; i < A.length; i++){
            angle += c[i];
            // System.out.println("angle = " + angle);
        }
        return angle;
    }

    static double verticeTurnProblemDegrees(double[] A, double[] B, double[] C)
    {
        if(A.length != B.length || A.length != C.length){
            return 0;
        }
        double[] CA = verticeSubtraction(C, A);
        double[] BA = verticeSubtraction(B, A);
        double[] Cn = verticeNormalize(CA);
        double[] Bn = verticeNormalize(BA);
        // System.out.println("radians = " + verticeMultiplication(Bn, Cn));
        if(verticeMultiplication(Bn, Cn) < 0){
            return Math.acos(verticeMultiplication(Bn, Cn)) * (-180) /  3.14159;
        }
        double angle = Math.acos(verticeMultiplication(Bn, Cn)) * 180 /  3.14159;
        return angle;
    }
}