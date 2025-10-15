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
        String name = stringField("name").trim();
        String email = stringField("email").trim();

        if (name.isEmpty() || email.isEmpty()) {
            throw new UserRegistrationFailedException(name, email);
        }

        try {
            _receiver.registerUser(name, email);

            int userId = _receiver.getAllUsers()
                .get(_receiver.getAllUsers().size() - 1)
                .getID();

            _display.popup(Message.registrationSuccessful(userId));

        } catch (Exception e) {
            // Caso o método do core lance uma exceção inesperada
            throw new UserRegistrationFailedException(name, email);
        }
    }
}
