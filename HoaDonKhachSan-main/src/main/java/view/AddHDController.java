package view;

import java.util.Date;

import usecase.AddHDUseCase;
import dto.AddHDInputDTO;

public class AddHDController {
    private final AddHDUseCase addHDUseCase;

    public AddHDController(AddHDUseCase addHDUseCase) {
        this.addHDUseCase = addHDUseCase;
    }

    public void addHoaDon(String maHDStr, String hoTen, Date ngayThue, boolean isHourBased, String durationStr, String donGiaStr) {
        int maHD = Integer.parseInt(maHDStr);
        int duration = Integer.parseInt(durationStr);
        double donGia = Double.parseDouble(donGiaStr);

       
        AddHDInputDTO inputDTO = new AddHDInputDTO(maHD, ngayThue, hoTen, isHourBased ? "SG" : "NG", donGia, isHourBased ? duration : 0, isHourBased ? 0 : duration);
        addHDUseCase.execute(inputDTO); 
    }
}
