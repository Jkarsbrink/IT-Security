package it.security;


import java.math.BigInteger;

public class KeyPair implements java.io.Serializable {
    private static final long serialVersionUID = 4L;
    private BigInteger key;
    private BigInteger n;

    public KeyPair(BigInteger key, BigInteger n) {
        this.key = key;
        this.n = n;
    }
    public BigInteger getN(){
        return this.n;
    }
    public void setN(BigInteger n){
        this.n =n;
    }

    public BigInteger getKey() {
        return this.key;
    }
    public void setKey(BigInteger key){
        this.key = key;
    }
}
