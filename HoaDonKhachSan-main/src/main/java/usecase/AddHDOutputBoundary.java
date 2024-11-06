package usecase;

import dto.AddHDInputDTO;
import dto.AddHDOutputDTO;

public interface AddHDOutputBoundary {
    void present(int newHoaDonId);

    void present(AddHDOutputDTO addOutputDTO);

    void onSuccess(AddHDInputDTO hdInputDTO);
}
