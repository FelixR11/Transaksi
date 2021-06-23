
package uas_kelompok_4;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
/**
 *
 * @author User
 */
public class Penjualan extends javax.swing.JFrame {

    /**
     * Creates new form Penjualan
     */
    public Penjualan() {
        initComponents();
    }
    
    File file = new File("C:\\Transaksi\\Data");
    private static String nama;
    String hasilakhir;
    String jenisBarang, namaBarang, tglMasuk, tglKadaluarsa, selectedComboBox, oldNama;
    int ln, hargaBarang, stokBarang, jumlahPembelian, stokTersedia;
    int price, outSubtotal, totalharga;
    int c1 = 0; int c2 = 0;
    int jumlah, total;

    class notepad {

        public notepad() {
        }
    }
    
    void createFolder(){
        if(!file.exists()){
            file.mkdirs();
        }
    }

    void readFile(String namaFile){
        try {
            FileReader fr = new FileReader(file + namaFile);
            System.out.println("File Exist!");
            fr.close();
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fw = new FileWriter(file + namaFile);
                System.out.println("File Created!");
                fw.close();
            } catch (IOException ex1) {
                Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    int checkData(String user, String pass, String namaFile){
        int nilai = 0;
        String Username, Password;
	try{
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            Username = raf.readLine();
            Password = raf.readLine();
            
            for(int i=0; i<(ln-2); i+=5){
                Username = raf.readLine().substring(11);
		Password = raf.readLine().substring(11);
                
                if(user.equals(Username) && pass.equals(Password)){
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    nilai = 1;
                    break;
		}else if(i == (ln-6)){
                    JOptionPane.showMessageDialog(null, "Username atau Password Salah!");
                    nilai = 0;
                    break;
		}
		for (int k=1; k<=3; k++){
                    raf.readLine();
		}
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(Transaksi.notepad.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Transaksi.notepad.class.getName()).log(Level.SEVERE, null, ex);
	}
        return nilai;
    }

    void countLines2(String namaFile){
        try {
            ln = 1;
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; raf.readLine()!=null; i++){
                ln++;
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void checkCombo(String namaFile){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            raf.readLine();
            for(int i=0; i<(ln-2); i+=7){
                namaBarang = raf.readLine().substring(14);
                cb_penjualan.addItem(namaBarang);
                if (i==(ln-6)){
                    break;
                }
                for(int k=1; k<=6; k++){
                    raf.readLine();
                }
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editRecord(String oldNama, String newStok){
        File oldFile = new File("c:\\Transaksi\\Data\\dataItems.txt");
        File newFile = new File("c:\\Transaksi\\Data\\temp.txt");
        String cekData;
        try{
            FileWriter fw = new FileWriter("c:\\Transaksi\\Data\\temp.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            countLines2("\\dataItems.txt");
            RandomAccessFile raf = new RandomAccessFile("c:\\Transaksi\\Data\\dataItems.txt", "rw");
            for(int i=0; i<(ln-2); i++){
                cekData = raf.readLine();
                if(cekData.equals(oldNama)){
                    pw.println(cekData);
                    cekData = raf.readLine();
                    pw.println(cekData);
                    pw.println(newStok);
                    raf.readLine();
                    i+=2;
                }
                else{
                    pw.println(cekData);
                } 
            }
            pw.println();
            pw.flush();
            raf.close();
            pw.close();
            bw.close();
            fw.close();
        }
        catch (Exception e) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editRecord2(){
        File oldFile1 = new File("c:\\Transaksi\\Data\\dataItems.txt");
        File oldFile2 = new File("c:\\Transaksi\\Data\\tempLogJualan.txt");
        countLines2("\\tempLogJualan.txt"); //WARNING : ini bisa create file secara otomatis
        c2 = ln;
        File newFile = new File("c:\\Transaksi\\Data\\temp2.txt");
        String cekData1 = ""; String cekData2 = ""; String cekData3 = "";
        int jumlah = 0; int jumlah2 = 0; int total = 0; int salah=0;
        try{
            FileWriter fw = new FileWriter("c:\\Transaksi\\Data\\temp2.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            RandomAccessFile raf = new RandomAccessFile("c:\\Transaksi\\Data\\dataItems.txt", "rw");
            for(int i=1; i<(c1-3); i+=7){
                cekData1 = raf.readLine();
                pw.println(cekData1);
                cekData1 = raf.readLine();
                RandomAccessFile raf2 = new RandomAccessFile("c:\\Transaksi\\Data\\tempLogJualan.txt", "rw");
                for(int k=1; k<(c2-3); k+=4){
                    cekData2 = "Nama Barang : "+raf2.readLine().substring(7);
                    if(cekData1.equals(cekData2)){
                        pw.println(cekData2);
                        cekData1 = raf.readLine();
                        pw.println(cekData1);
                        cekData1 = raf.readLine().substring(8);
                        jumlah = Integer.parseInt(cekData1);
                        cekData2 = raf2.readLine().substring(9);
                        jumlah2 = Integer.parseInt(cekData2);
                        total = jumlah + jumlah2;
                        pw.println("Stock : "+total);
                        cekData1 = raf.readLine();
                        pw.println(cekData1);
                        cekData1 = raf.readLine();
                        pw.println(cekData1);
                        cekData1 = raf.readLine();
                        pw.println(cekData1);
                        raf2.readLine();
                        raf2.readLine();
                        salah = 0;
                        break;
                    }
                    else {
                        raf2.readLine();
                        raf2.readLine();
                        raf2.readLine();
                        salah = 1;
                    }
                }
                if(salah == 1){
                    pw.println(cekData1);
                    for(int j=1; j<=5; j++){
                    cekData1 = raf.readLine();
                    pw.println(cekData1);
                    }
                }
                raf2.close();
            }
            pw.flush();
            raf.close();
            pw.close();
            bw.close();
            fw.close();
        }
        catch (Exception e) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void gantiFile(String namaFile){
        try{
            File oldFile = new File(file + "\\dataItems.txt");
            oldFile.delete();
            File dump = new File(file + "\\dataItems.txt");
            File newFile = new File(file + namaFile);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("gagal");
        }
    }

    void checkStock(String namaFile){
        try {
            DefaultTableModel model = (DefaultTableModel) tb_items.getModel();
            int rowCount = model.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; i<(ln-2); i+=7){
                jenisBarang = raf.readLine().substring(15);
                namaBarang = raf.readLine().substring(14);
                stokBarang = Integer.parseInt(raf.readLine().substring(8));
                hargaBarang = Integer.parseInt(raf.readLine().substring(8));
                tglMasuk = raf.readLine().substring(16);
                tglKadaluarsa = raf.readLine().substring(21);
                model.addRow(new Object[]{jenisBarang, namaBarang, stokBarang, hargaBarang, tglMasuk, tglKadaluarsa});
                if (i==(ln-6)){
                    break;
                }
                for(int k=1; k<=2; k++){
                    raf.readLine();
                }
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void checkLogJual(String namaFile){
        try {
            File checkEmptyFile = new File(file + namaFile);
            if (checkEmptyFile.length() == 0) {
                DefaultTableModel model = (DefaultTableModel) tb_jual.getModel();
                int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--){
                    model.removeRow(i);
                }
            }
            else{
                DefaultTableModel model = (DefaultTableModel) tb_jual.getModel();
                int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
                for(int i=1; i<=ln; i+=4){
                    namaBarang = raf.readLine().substring(7);
                    stokBarang = Integer.parseInt(raf.readLine().substring(9));
                    totalharga = Integer.parseInt(raf.readLine().substring(11));
                    model.addRow(new Object[]{namaBarang, stokBarang, totalharga});
                    if (i==(ln-4)){
                        break;
                    }
                    for(int k=1; k<=1; k++){
                        raf.readLine();
                    }
                }
                raf.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int checkData2(String namaBrg, String namaFile){
        int nilai = 0;
        try {
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            namaBarang = raf.readLine();
            for(int i=0; i<(ln-2); i+=7){
                namaBarang = raf.readLine();
                if(namaBrg.equals(namaBarang)){
                    price = Integer.parseInt(raf.readLine().substring(8));
                    stokTersedia = Integer.parseInt(raf.readLine().substring(8));
                    nilai = 1;
                    break;
                }
                else if (i==(ln-6)){
                    //System.out.println("Stok tdk tersedia");
                    nilai = 0;
                    break;
                }
                for(int k=1; k<=6; k++){
                    raf.readLine();
                }
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nilai;
    }

    //perlu di cek lg variablenye
//    void calcAndSetTotal() {
//        int sum = price;
//
//        if (!jumlahTextField.getText().isEmpty()) {
//            sum *= Integer.parseInt(jumlahTextField.getText());//we must add this
//            subtotalTextField.setText(String.valueOf(sum));
//        }
//        else{
//            subtotalTextField.setText("0");
//        }
//    }

    void addData2(String namaBrg, int jumlahbrg, int subtotal, String namaFile){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; i<ln; i++){
                raf.readLine();
            }
            raf.writeBytes("Nama : "+namaBrg+"\r\n");
            raf.writeBytes("Jumlah : "+jumlahbrg+"\r\n");
            raf.writeBytes("Subtotal : "+subtotal+"\r\n");
            raf.writeBytes("\r\n");
            raf.close();
            }
        catch (FileNotFoundException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Penjualan.notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tx_tJumlah = new javax.swing.JTextField();
        tx_tTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bt_tambah = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bt_logout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bt_cancle = new javax.swing.JButton();
        bt_refresh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        bt_bayar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sp_items = new javax.swing.JScrollPane();
        tb_items = new javax.swing.JTable();
        sp_jual = new javax.swing.JScrollPane();
        tb_jual = new javax.swing.JTable();
        cb_penjualan = new javax.swing.JComboBox<>();
        tx_bTotal = new javax.swing.JTextField();
        tx_bCash = new javax.swing.JTextField();
        tx_bKembali = new javax.swing.JTextField();
        tx_tStok = new javax.swing.JTextField();
        tx_tHarga = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tx_tJumlah.setOpaque(false);

        tx_tTotal.setEditable(false);
        tx_tTotal.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form Penjualan");

        bt_tambah.setText("Tambah");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Daftar Belanjaan");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        bt_logout.setText("Logout");
        bt_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_logoutActionPerformed(evt);
            }
        });

        jLabel3.setText("Total");

        jLabel4.setText("Cash");

        bt_cancle.setText("Cancle");
        bt_cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancleActionPerformed(evt);
            }
        });

        bt_refresh.setText("Refresh");

        jLabel5.setText("Kembali");

        bt_bayar.setText("Bayar");

        jLabel6.setText("Nama Barang");

        jLabel7.setText("Stok");

        jLabel8.setText("Harga");

        jLabel9.setText("Jumlah");

        jLabel10.setText("Total");

        tb_items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jenis", "Nama", "Stok", "Harga"
            }
        ));
        sp_items.setViewportView(tb_items);

        tb_jual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jenis", "Nama", "Jumlah", "Haga", "Total"
            }
        ));
        sp_jual.setViewportView(tb_jual);

        cb_penjualan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- pilih -" }));

        tx_tStok.setEditable(false);
        tx_tStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_tStokActionPerformed(evt);
            }
        });

        tx_tHarga.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_jual)
                    .addComponent(sp_items)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bt_cancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bt_bayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tx_bKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tx_bCash, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_bTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_penjualan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tx_tStok)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tx_tHarga)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_tJumlah)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_tTotal)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_items, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_tStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_tHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_tJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_tambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_bTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_bCash, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tx_bKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tx_tStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_tStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_tStokActionPerformed

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.startFrameLogin();
        setVisible(false);

        Transaksi transaksi = new Transaksi();
        transaksi.nilai = 0;
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void bt_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancleActionPerformed
        // TODO add your handling code here:
        Transaksi transaksi = new Transaksi();
        transaksi.createFolder();
        transaksi.readFile("\\dataManager.txt");
        countLines2("\\dataManager.txt");
        
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        Object[] message = {
            "Username : ", username,
            "Password : ", password
        };
        
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        
        if(checkData(username.getText(), password.getText(), "\\dataManager.txt") == 1){
            
            	
        }else{
            JOptionPane.showMessageDialog(null, "Gagal Membatalkan pesanan!");
        }
    }//GEN-LAST:event_bt_cancleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_bayar;
    private javax.swing.JButton bt_cancle;
    private javax.swing.JButton bt_logout;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JComboBox<String> cb_penjualan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane sp_items;
    private javax.swing.JScrollPane sp_jual;
    private javax.swing.JTable tb_items;
    private javax.swing.JTable tb_jual;
    private javax.swing.JTextField tx_bCash;
    private javax.swing.JTextField tx_bKembali;
    private javax.swing.JTextField tx_bTotal;
    private javax.swing.JTextField tx_tHarga;
    private javax.swing.JTextField tx_tJumlah;
    private javax.swing.JTextField tx_tStok;
    private javax.swing.JTextField tx_tTotal;
    // End of variables declaration//GEN-END:variables
}
