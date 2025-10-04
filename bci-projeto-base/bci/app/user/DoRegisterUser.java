package bci.app.user;

import bci.core.LibraryManager;
import bci.app.exception.UserRegistrationFailedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 
4.2.1. Registar utente.*/
class DoRegisterUser extends Command<LibraryManager> {

    DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        addStringField("name", Prompt.userName());
        addStringField("email", Prompt.userEMail());

    }

    @Override
    protected final void execute() throws CommandException {
        String name = stringField("name");
        String email = stringField("email");


        // 1. Regista o utente.
        // Se o método '_receiver.registerUser(name, email)' lançar uma exceção não-verificada 
        // (como RuntimeException), ela será propagada.
        _receiver.registerUser(name, email);
    }
}
