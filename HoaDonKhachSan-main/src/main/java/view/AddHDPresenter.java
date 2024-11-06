package view;

import usecase.AddHDOutputBoundary;
import dto.AddHDInputDTO;
import dto.AddHDOutputDTO;

public class AddHDPresenter implements AddHDOutputBoundary{
    private int newHDId;
    private AddHDOutputDTO  addHDOutputDTO= null;
    private final MauHD view;

    @Override
    public void present(int newHDId) {

        this.newHDId = newHDId;
    }

    public int getnewHDId() {
        return newHDId;
    }

    @Override
public void present(AddHDOutputDTO addHDOutputDTO) {
    this.addHDOutputDTO = addHDOutputDTO;
    // Có thể thêm thông báo hoặc cập nhật GUI ở đây nếu cần.
}


    public AddHDOutputDTO getThemHDOutputDTO() {
        return addHDOutputDTO;
    }

    @Override
public void onSuccess(AddHDInputDTO hdInputDTO) {
    // Add your success handling logic here
    view.addInvoiceToTable(hdInputDTO);
}
    public AddHDPresenter(MauHD view) {
    this.view = view;
}

}


