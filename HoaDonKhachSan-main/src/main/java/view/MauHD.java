package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dto.AddHDInputDTO;

public class MauHD extends JFrame {
    private JTextField tenNguoiThueField, ngayThueField, maHoaDonField, soGioThueField, giaGioField, soNgayThueField, giaNgayField;
    private JComboBox<String> loaiHoaDonComboBox;
    private JPanel dynamicPanel, theoGioPanel, theoNgayPanel;
    private JButton nutAddHD,nutXoaHD, nutSuaHD, nutTimHD;
    private JTable table;
    private DefaultTableModel tableModel;
    private AddHDController addHDController;

    public MauHD() {
        setTitle("Biên Lai Hóa Đơn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel thongTinChungPanel = new JPanel();
        thongTinChungPanel.setLayout(new BoxLayout(thongTinChungPanel, BoxLayout.Y_AXIS));
        thongTinChungPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Người Thuê"));

        tenNguoiThueField = new JTextField(20);
        ngayThueField = new JTextField(20);
        maHoaDonField = new JTextField(20);
        loaiHoaDonComboBox = new JComboBox<>(new String[]{"Hóa Đơn Theo Giờ", "Hóa Đơn Theo Ngày"});

        thongTinChungPanel.add(createInputLabelPanel("Họ Tên:", tenNguoiThueField));
        thongTinChungPanel.add(createInputLabelPanel("Ngày thuê (dd/MM/yyyy):", ngayThueField));
        thongTinChungPanel.add(createInputLabelPanel("Mã hóa đơn:", maHoaDonField));
        thongTinChungPanel.add(createInputLabelPanel("Loại Hóa Đơn:", loaiHoaDonComboBox));

        dynamicPanel = new JPanel(new CardLayout());
        theoGioPanel = createTheoGioPanel();
        theoNgayPanel = createTheoNgayPanel();

        dynamicPanel.add(theoGioPanel, "TheoGio");
        dynamicPanel.add(theoNgayPanel, "TheoNgay");

        CardLayout cl = (CardLayout) dynamicPanel.getLayout();
        cl.show(dynamicPanel, "TheoGio");

        loaiHoaDonComboBox.addActionListener(e -> {
            String selectedType = (String) loaiHoaDonComboBox.getSelectedItem();
            if ("Hóa Đơn Theo Giờ".equals(selectedType)) {
                cl.show(dynamicPanel, "TheoGio");
            } else {
                cl.show(dynamicPanel, "TheoNgay");
            }
        });

        JPanel nutPanel = new JPanel(new FlowLayout());
        nutAddHD = new JButton("Thêm Hóa Đơn");
        nutXoaHD = new JButton("Xóa Hóa Đơn");
        nutSuaHD = new JButton("Sửa Hóa Đơn");
        nutTimHD = new JButton("Tìm Hóa Đơn");

        nutPanel.add(nutAddHD);
        nutPanel.add(nutXoaHD);
        nutPanel.add(nutSuaHD);
        nutPanel.add(nutTimHD);

        nutAddHD.addActionListener(this::AddHD);
        
        // Placeholder button actions
        // nutXoaHD.addActionListener(e -> deleteHD());
        // nutTimHD.addActionListener(e -> searchHD()); 

        tableModel = new DefaultTableModel(new String[]{"Mã Hóa Đơn", "Tên Người Thuê", "Ngày Thuê", "Loại Hóa Đơn", "Số Giờ/Ngày Thuê", "Giá"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(thongTinChungPanel, BorderLayout.NORTH);
        mainPanel.add(dynamicPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(nutPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createTheoGioPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Nhập Theo Giờ"));
        soGioThueField = new JTextField(10);
        giaGioField = new JTextField(10);
        panel.add(new JLabel("Số Giờ Thuê:"));
        panel.add(soGioThueField);
        panel.add(new JLabel("Giá Theo Giờ:"));
        panel.add(giaGioField);
        return panel;
    }

    private JPanel createTheoNgayPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Nhập Theo Ngày"));
        soNgayThueField = new JTextField(10);
        giaNgayField = new JTextField(10);
        panel.add(new JLabel("Số Ngày Thuê:"));
        panel.add(soNgayThueField);
        panel.add(new JLabel("Giá Theo Ngày:"));
        panel.add(giaNgayField);
        return panel;
    }

    private JPanel createInputLabelPanel(String labelText, Component inputComponent) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(inputComponent);
        return panel;
    }

    private void AddHD(ActionEvent e) {
        try {
            String hoTen = tenNguoiThueField.getText();
            String ngayThueStr = ngayThueField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayThue = dateFormat.parse(ngayThueStr);

            String loaiHoaDon = (String) loaiHoaDonComboBox.getSelectedItem();
            boolean isHourBased = "Hóa Đơn Theo Giờ".equals(loaiHoaDon);

            addHDController.addHoaDon(maHoaDonField.getText(), hoTen, ngayThue, isHourBased,
                    isHourBased ? soGioThueField.getText() : soNgayThueField.getText(),
                    isHourBased ? giaGioField.getText() : giaNgayField.getText());

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd/MM/yyyy.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void addInvoiceToTable(AddHDInputDTO dto) {
        Object[] rowData = new Object[]{
            dto.getmaHD(),
            dto.gethoTen(),
            dto.getngayHD(),
            dto.getkHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày",
            dto.getkHD().equals("SG") ? dto.getsoGioThue() : dto.getsoNgayThue(),
            dto.getdonGia()
        };
        tableModel.addRow(rowData);
    }
    public void setAddHDController(AddHDController addHDController) {
        this.addHDController = addHDController;
    }
}
