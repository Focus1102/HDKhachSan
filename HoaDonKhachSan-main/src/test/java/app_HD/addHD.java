package app_HD;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import database.HDDAOMemory;
import usecase.AddHDInputBoundary;
import usecase.AddHDUseCase;
import dto.AddHDInputDTO;
import dto.AddHDOutputDTO;
import view.AddHDPresenter;


public class addHD {
    @Test
    public void testAddHD(){
        HDDAOMemory addHDDAOMemory = new HDDAOMemory();
        AddHDPresenter addHDPresenter = new AddHDPresenter(null);
        AddHDInputBoundary addHDInputBoundary = null;
        addHDInputBoundary = new AddHDUseCase(addHDDAOMemory, addHDPresenter);
        addHDInputBoundary.execute(getMockHD());
        AddHDOutputDTO addHDOutputDTO = addHDPresenter.getThemHDOutputDTO();
        assertEquals(getMockHD().gethoTen(),addHDOutputDTO.gethoTen());
    }

    private AddHDInputDTO getMockHD() {
        AddHDInputDTO hd1 = null;
        Calendar calendar1 = Calendar.getInstance();

        calendar1.set(Calendar.YEAR,2003);
        calendar1.set(Calendar.MONTH,Calendar.MAY);
        calendar1.set(Calendar.DAY_OF_MONTH,12);

        Date date = calendar1.getTime();

        hd1 = new AddHDInputDTO(21, date, "teo", "theogio", 20, 200, 0);
        return hd1;
    }
}
