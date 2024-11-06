package usecase;

import entity.HoaDon;
import dto.AddHDInputDTO;

public interface AddHDInputBoundary {
    HoaDon execute(AddHDInputDTO hdInputDTO);

 
}
