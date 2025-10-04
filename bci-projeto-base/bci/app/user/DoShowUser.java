package bci.app.user;
import bci.core.User;
import bci.core.LibraryManager;
import bci.app.exception.NoSuchUserException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 
4.2.2. Show specific user.*/
class DoShowUser extends Command<LibraryManager> {

    DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
        // Adiciona o campo para o id do utente
        addIntegerField("id", Prompt.userId());
    }

    @Override
    protected final void execute() throws CommandException {
        int id = integerField("id");

        try {
            User user = _receiver.getUserById(id); // tenta obter o utente

            if (user == null) {
                throw new NoSuchUserException(id);
            }

            // Mostra a informação do utente usando o toString() do User
            _display.popup(user.toString());

        } catch (NoSuchUserException e) {
            throw e; // a framework trata a exibição do erro
        }
    }
}
