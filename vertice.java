public class vertice {
    
    public static void main(String[] args) throws Exception
    {
        double[] a3 = {randomVertex(), randomVertex(), randomVertex()};
        double[] b3 = {basedVertex(a3[0], 2), basedVertex(a3[1], 2), basedVertex(a3[2], 4)};
        double[] c3 = {basedVertex(a3[0], 1.5), basedVertex(a3[1], 5), basedVertex(a3[2], 2)};
        double[] a = {randomVertex(), randomVertex()};
        double[] b = {basedVertex(a[0], 2), basedVertex(a[1], 2)};
        double[] c = {basedVertex(a[0], 1.5), basedVertex(a[1], 5)};
        System.out.println(verticeCrossProduct(a3, b3));
        // a = negGen(a);
        // b = negGen(b);
        // c = negGen(c);
        System.out.println(printVertex(a, "a"));
        System.out.println(printVertex(b, "b"));
        System.out.println(printVertex(c, "c"));
        System.out.println(verticeTurnProblemDegrees(a, b, c) + " degrees, without rounding");

        System.out.println(printVertex(a3, "a3"));
        System.out.println(printVertex(b3, "b3"));
        System.out.println(printVertex(c3, "c3"));
        System.out.println(verticeTurnProblemDegrees(a3, b3, c3) + " degrees, without rounding");

        // double[] aa = {5, 12};
        // double[] ab = {10, 5};
        // double[] ac = {4, 9};
        // System.out.println(verticeTurnProblemDegrees(aa, ab, ac) + " degrees, without rounding");

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

    static double verticeCrossProduct(double[] A, double[] B)
    {
        double c = 0;
        if(A.length != B.length && A.length != 3){
            return c;
        }
        c = A[1] * B[2] - A[2] * B[1] + A[2] * B[0] - A[0] * B[2] + A[0] * B[1] - A[1] * B[0];
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

    static double randomVertex()
    {
        return ((double) Math.round((Math.random() * 10) * 10) / 10);
    }

    static double basedVertex(Double a, double w)
    {
        return ((double) Math.round(a * w * 10) / 10);
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
        System.out.println("radians = " + verticeMultiplication(Bn, Cn));
        if(verticeMultiplication(Bn, Cn) < 0){
            return (Math.acos(verticeMultiplication(Bn, Cn)) * (180) /  3.14159) * -1;
        }
        double angle = Math.acos(verticeMultiplication(Bn, Cn)) * 180 /  3.14159;
        return angle;
    }

    static String printVertex(double[] A, String name)
    {
        String vertex = "";
        for(int i = 0; i < A.length; i++){
            if(i != A.length - 1){
                vertex += A[i] + " " + name + "[" + i + "], ";
            }
            else{
                vertex += A[i] + " " + name + "[" + i + "]";
            }
        }
        return vertex;
    }

    static double[] negGen(double[] A){
        double[] c = new double[A.length];
        double split = 1.0 / (A.length + 1);
        double negGen = Math.random();
        // System.out.println(negGen);
        if(A.length == 2){
            if(negGen >= 0 && negGen <= split){
                c[0] = A[0] * -1;
                c[1] = A[1] * -1;
            }
            else if(negGen > split && negGen <= 2*split){
                c[0] = A[0];
                c[1] = A[1] * -1;
            }
            else{
                c[0] = A[0];
                c[1] = A[1];
            }
        }
        if(A.length == 3){
            if(negGen >= 0 && negGen <= split){
                c[0] = A[0] * -1;
                c[1] = A[1] * -1;
                c[2] = A[2] * -1;
            }
            else if(negGen > split && negGen <= 2*split){
                c[0] = A[0];
                c[1] = A[1] * -1;
                c[2] = A[2] * -1;

            }
            else if(negGen > 2*split && negGen <= 3*split){
                c[0] = A[0];
                c[1] = A[1];
                c[2] = A[2] * -1;

            }
            else{
                c[0] = A[0];
                c[1] = A[1];
                c[2] = A[2];
            }
        }
        if(A.length == 4){
            if(negGen >= 0 && negGen <= split){
                c[0] = A[0] * -1;
                c[1] = A[1] * -1;
                c[2] = A[2] * -1;
                c[3] = A[3] * -1;
            }
            else if(negGen > split && negGen <= 2*split){
                c[0] = A[0];
                c[1] = A[1] * -1;
                c[2] = A[2] * -1;
                c[3] = A[3] * -1;

            }
            else if(negGen > 2*split && negGen <= 3*split){
                c[0] = A[0];
                c[1] = A[1];
                c[2] = A[2] * -1;
                c[3] = A[3] * -1;

            }
            else if(negGen > 3*split && negGen <= 4*split){
                c[0] = A[0];
                c[1] = A[1];
                c[2] = A[2];
                c[3] = A[3] * -1;

            }
            else{
                c[0] = A[0];
                c[1] = A[1];
                c[2] = A[2];
                c[3] = A[3];
            }
        }
        return c;
    }
}