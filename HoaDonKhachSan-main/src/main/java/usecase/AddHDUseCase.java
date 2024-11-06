package usecase;

import entity.HoaDon;
import dto.AddHDInputDTO;
import entity.HDTheoGio;
import entity.HDTheoNgay;

public class AddHDUseCase implements AddHDInputBoundary {
    private final AddHDDatabaseBoundary addHDDBB;
    private final AddHDOutputBoundary addHDOB;

    public AddHDUseCase(AddHDDatabaseBoundary addHDDB, AddHDOutputBoundary addHDOB) {
        this.addHDDBB = addHDDB;
        this.addHDOB = addHDOB;
    }

    @Override
public HoaDon execute(AddHDInputDTO hdInputDTO) {
    if (hdInputDTO.getmaHD() <= 0 || hdInputDTO.getdonGia() < 0 || hdInputDTO.gethoTen().isEmpty()) {
        throw new IllegalArgumentException("Invalid data provided.");
    }

    HoaDon hoaDon;
    if (hdInputDTO.getkHD().equals("SG")) {
        hoaDon = new HDTheoGio(hdInputDTO.getmaHD(), hdInputDTO.getngayHD(), hdInputDTO.gethoTen(), hdInputDTO.getdonGia(), hdInputDTO.getsoGioThue());
    } else {
        hoaDon = new HDTheoNgay(hdInputDTO.getmaHD(), hdInputDTO.getngayHD(), hdInputDTO.gethoTen(), hdInputDTO.getdonGia(), hdInputDTO.getsoNgayThue());
    }

    addHDDBB.addHoadon(hoaDon);


    addHDOB.onSuccess(hdInputDTO); 

    return hoaDon; 
}
}
    
