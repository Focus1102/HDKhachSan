package view;

import database.HDDAOMemory;
import usecase.AddHDUseCase;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MauHD form = new MauHD();
            form.setVisible(true);

            HDDAOMemory addHDDAOMemory = new HDDAOMemory();
            AddHDPresenter addHDPresenter = new AddHDPresenter(form);
            AddHDUseCase addHDUseCase = new AddHDUseCase(addHDDAOMemory, addHDPresenter);

            AddHDController addHDController = new AddHDController(addHDUseCase);
            form.setAddHDController(addHDController);
        });
    }
}
