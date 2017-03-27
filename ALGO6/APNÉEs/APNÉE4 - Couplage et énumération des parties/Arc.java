class Arc {
    int numero, source, destination;

    Arc(int n, int s, int d) {
        numero = n;
        source = s;
        destination = d;
    }

    public String toString() {
        return numero + "/" + (source+1) + "+" + (destination+1) + "/";
    }
}
