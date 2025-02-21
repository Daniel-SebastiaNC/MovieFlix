package br.com.movieflix.exception;

public class UsernameOrPasswordInvaldException extends RuntimeException{

    public UsernameOrPasswordInvaldException(String mensage){
        super(mensage);
    }
}
