/*
INI ADALAH TEST COLLAB
 */
package uas_kelompok_4;

/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Transaksi extends JFrame{
    public void runApp(){
	Login login = new Login();
	login.startFrameLogin();
    }

    public static void main(String[] args) {
	Transaksi transaksi = new Transaksi();
	transaksi.runApp();
    }

    File file = new File("C:\\Transaksi\\Data");
    String Nama, Alamat, Username, Password, jenisBarang, namaBarang;
    int baris, nilai = 0, nilai2 = 0, a = 1, b = 0;

    class notepad{
	public notepad(){

    	}
    }

    void createFolder(){
	if(!file.exists()){
		file.mkdirs();
	}
    }

    void readFile(String namaFile){
	try{
            FileReader fr = new FileReader(file + namaFile);
            System.out.println("File Exist!");
	}catch(FileNotFoundException ex){
            try{
                FileWriter fw = new FileWriter(file + namaFile);
                System.out.println("File Created!");
            }catch(IOException ex1){
                Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    void cekFile(String namaFile){
        try{
            FileReader fr = new FileReader(file + namaFile);
            System.out.println("File Exist!");

            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; raf.readLine()!=null; i++){
                b++;
            }
        }catch(FileNotFoundException ex){
            a = 0;
        }catch(IOException ex1){
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex1);
        }		
    }

    void addData(String nama, String alamat, String user, String pass, String namaFile){
        try{
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; i<baris; i++){
            raf.readLine();
	}

            raf.writeBytes("Nama : " + nama + "\r\n");
            raf.writeBytes("Alamat : " + alamat + "\r\n");
            raf.writeBytes("Username : " + user + "\r\n");
            raf.writeBytes("Password : " + pass + "\r\n");
            raf.writeBytes("\r\n");
            JOptionPane.showMessageDialog(null, "Registerasi Berhasil!");
	}catch(FileNotFoundException ex){
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
	}catch(IOException ex){
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    int checkData(String user, String pass, String namaFile){
        nilai = 0;
	try{
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            Username = raf.readLine();
            Password = raf.readLine();
            
            for(int i=0; i<(baris-2); i+=5){
                Username = raf.readLine().substring(11);
		Password = raf.readLine().substring(11);
                
                if(user.equals(Username) && pass.equals(Password)){
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    nilai = 1;
                    break;
		}else if(i == (baris-6)){
                    JOptionPane.showMessageDialog(null, "Username atau Password Salah!");
                    nilai = 0;
                    break;
		}
		for (int k=1; k<=3; k++){
                    raf.readLine();
		}
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
	}
        return nilai;
    }

    void countLines(String namaFile){
        try {
            baris = 1;
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; raf.readLine()!=null; i++){
                baris++;
            }
            System.out.println("Number of lines: " + baris);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    void addData2(String jenis, String namaBrg, int hargaBrg, int stokBrg, String tglMsk, String tglKdlrsa, String namaFile){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            for(int i=0; i<baris; i++){
                raf.readLine();
            }
            raf.writeBytes("Jenis Barang : " + jenis + "\r\n");
            raf.writeBytes("Nama Barang : " + namaBrg + "\r\n");
            raf.writeBytes("Harga : " + hargaBrg + "\r\n");
            raf.writeBytes("Stock : " + stokBrg + "\r\n");
            raf.writeBytes("Tanggal Masuk : " + tglMsk + "\r\n");
            raf.writeBytes("Tanggal Kadaluarsa : " + tglKdlrsa + "\r\n");
            raf.writeBytes("\r\n");
            }
        catch (FileNotFoundException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int checkData2(String namaBrg, String namaFile){
        nilai2 = 0;
        try {
            RandomAccessFile raf = new RandomAccessFile(file + namaFile, "rw");
            namaBarang = raf.readLine();
            for(int i=0; i<(baris-2); i+=7){
                namaBarang = raf.readLine().substring(14);
                if(namaBrg.equals(namaBarang)){
                    JOptionPane.showMessageDialog(null, "Nama Barang Sudah Terdaftar!");
                    nilai2 = 1;
                    break;
                }
                else if (i==(baris-6)){
                    nilai2 = 0;
                    break;
                }
                for(int k=1; k<=6; k++){
                    raf.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(notepad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nilai2;
    }

    public int getA(){
    	return a;
    }

    public int getB(){
	return b;
    }
}

class Login extends JFrame implements ActionListener{
    private JFrame loginFrame;
    private JLabel lb_Login, lb_lgUsername, lb_lgPw;
    private JTextField tx_lgUsername;
    private JPasswordField pf_lgPw;
    private JRadioButton rb_admin, rb_kasir, rb_manager;
    private JButton bt_Login, bt_register;
    private ButtonGroup bg_login;
    private String user;

    public Login(){
        loginFrame = new JFrame("Login");
	lb_Login = new JLabel("Form Login", SwingConstants.CENTER);
	lb_lgUsername = new JLabel("Username :");
	tx_lgUsername = new JTextField(20);
	lb_lgPw = new JLabel("Password :");
        pf_lgPw = new JPasswordField(20);
        bt_Login = new JButton("Login");
        bt_register = new JButton("Register Admin");

        bg_login = new ButtonGroup();
        rb_admin = new JRadioButton("Admin");
        rb_kasir = new JRadioButton("Kasir");
        rb_manager = new JRadioButton("Manager");

        Transaksi transaksi = new Transaksi();
        transaksi.cekFile("\\dataAdmin.txt");
        int a = transaksi.getA();
        int b = transaksi.getB();
        if(a == 0 && b == 0){
                bt_register.setVisible(true);
        }else if(a != 0 && b != 0){
                bt_register.setVisible(false);
        }

        bt_Login.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                        bt_LoginActionPerformed(evt);
                }
        });

        bt_register.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                        bt_registerActionPerformed(evt);
                }
        });

        rb_admin.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                        rb_adminActionPerformed(evt);
                }
        });

        rb_kasir.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                        rb_kasirActionPerformed(evt);
                }
        });

        rb_manager.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                        rb_managerActionPerformed(evt);
                }
        });

        bt_register.addActionListener(this);
        bt_Login.addActionListener(this);
        rb_admin.addActionListener(this);
        rb_kasir.addActionListener(this);
        rb_manager.addActionListener(this);

        JPanel loginPanel1 = new JPanel();
        JPanel loginPanel2 = new JPanel();
        JPanel loginPanel3 = new JPanel();
        JPanel loginPanel4 = new JPanel();
        loginPanel1.setLayout(new GridLayout(4,1));
        loginPanel2.setLayout(new GridLayout(4,2));
        loginPanel3.setLayout(new GridLayout(1,3));
        loginPanel4.setLayout(new GridLayout(2,1));

        // loginPanel1.setLayout(new GridLayout(10,1));

        bg_login.add(rb_admin);
        bg_login.add(rb_kasir);
        bg_login.add(rb_manager);
        rb_admin.setSelected(true);

        loginPanel2.add(lb_lgUsername);
        loginPanel2.add(tx_lgUsername);
        loginPanel2.add(lb_lgPw);
        loginPanel2.add(pf_lgPw);
        loginPanel3.add(rb_admin);
        loginPanel3.add(rb_kasir);
        loginPanel3.add(rb_manager);
        loginPanel4.add(bt_Login, "CENTER");
        loginPanel4.add(bt_register, "CENTER");
        loginPanel1.add(lb_Login, BorderLayout.SOUTH);
        loginPanel1.add(loginPanel2);
        loginPanel1.add(loginPanel3);
        loginPanel1.add(loginPanel4);

        // loginPanel1.add(lb_Login, BorderLayout.SOUTH);
        // loginPanel1.add(lb_lgUsername);
        // loginPanel1.add(tx_lgUsername);
        // loginPanel1.add(lb_lgPw);
        // loginPanel1.add(pf_lgPw);
        // loginPanel1.add(rb_admin);
        // loginPanel1.add(rb_kasir);
        // loginPanel1.add(rb_manager);
        // loginPanel1.add(bt_Login, "CENTER");

        loginFrame.add(loginPanel1);
        loginFrame.setLocationRelativeTo(null);
    }

    public void startFrameLogin(){
        loginFrame.setSize(300,350);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = loginFrame.getSize();
        loginFrame.setLocation(((screenSize.width - frameSize.width) / 2),
        ((screenSize.height - frameSize.height) / 2));

        // loginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    void rb_adminActionPerformed(java.awt.event.ActionEvent evt){

    }

    void rb_kasirActionPerformed(java.awt.event.ActionEvent evt){

    }

    void rb_managerActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_LoginActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_registerActionPerformed(java.awt.event.ActionEvent evt){

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bt_Login && this.rb_admin.isSelected()){
            // login admin (Register)
            user = tx_lgUsername.getText();
            Transaksi transaksi = new Transaksi();
            transaksi.createFolder();
            transaksi.readFile("\\dataAdmin.txt");
            transaksi.countLines("\\dataAdmin.txt");

            if(transaksi.checkData(tx_lgUsername.getText(), pf_lgPw.getText(), "\\dataAdmin.txt") == 1){
            	Register register = new Register();
            	register.startFrameRegister();
            	loginFrame.setVisible(false);
            }
	}else if(ae.getSource() == bt_Login && this.rb_kasir.isSelected()){
            // Login Kasir
            user = tx_lgUsername.getText();
            Transaksi transaksi = new Transaksi();
            transaksi.createFolder();
            transaksi.readFile("\\dataKasir.txt");
            transaksi.countLines("\\dataKasir.txt");

            if(transaksi.checkData(tx_lgUsername.getText(), pf_lgPw.getText(), "\\dataKasir.txt") == 1){
            	// CLASS penjualan
            	loginFrame.setVisible(false);
            }
	}else if(ae.getSource() == bt_Login && this.rb_manager.isSelected()){
            // Login Manager (Items)
            user = tx_lgUsername.getText();
            Transaksi transaksi = new Transaksi();
            transaksi.createFolder();
            transaksi.readFile("\\dataManager.txt");
            transaksi.countLines("\\dataManager.txt");

            if(transaksi.checkData(tx_lgUsername.getText(), pf_lgPw.getText(), "\\dataAdmin.txt") == 1){
            	ManagerMenu mMenu = new ManagerMenu();
                mMenu.setVisible(true);
            	loginFrame.setVisible(false);
            }
	}else if(ae.getSource() == bt_register){
            RegisAdmin reg = new RegisAdmin();
            reg.startFrameRegisAdmin();
            loginFrame.setVisible(false);
	}
    }
}

class Register extends JFrame implements ActionListener{
    private JFrame registerFrame;
    private JLabel lb_rfnama, lb_rfalamat, lb_rfusername, lb_rfpw, lb_register;
    private JTextField tx_rfnama, tx_rfalamat, tx_rfusername;
    private JPasswordField pf_rfpw;
    private JButton bt_register, bt_clear, bt_logout;
    private JRadioButton rb_rfkasir, rb_rfmanager, rb_rfadmin;
    private ButtonGroup bg_register;


    public Register(){
        registerFrame = new JFrame("Register");
        lb_register = new JLabel("Register", SwingConstants.CENTER);
        lb_rfnama = new JLabel("Nama :");
        lb_rfalamat = new JLabel("Alamat :");
        lb_rfusername = new JLabel("Username :");
        lb_rfpw = new JLabel("Password :");
        tx_rfnama = new JTextField(20);
        tx_rfalamat = new JTextField(20);
        tx_rfusername = new JTextField(20);
        pf_rfpw = new JPasswordField(20);
        bg_register = new ButtonGroup();
        rb_rfadmin = new JRadioButton("Admin");
        rb_rfkasir = new JRadioButton("Kasir");
        rb_rfmanager = new JRadioButton("Manager");
        bt_register = new JButton("Register");
        bt_clear = new JButton("Clear");
        bt_logout = new JButton("Logout");

        bg_register.add(rb_rfadmin);
        bg_register.add(rb_rfkasir);
        bg_register.add(rb_rfmanager);
        rb_rfadmin.setSelected(true);

        JPanel registerPanel1 = new JPanel();
        JPanel registerPanel2 = new JPanel();
        JPanel registerPanel3 = new JPanel();
        JPanel registerPanel4 = new JPanel();

        registerPanel1.setLayout(new GridLayout(4,1));
        registerPanel2.setLayout(new GridLayout(4,2));
        registerPanel3.setLayout(new GridLayout(1,3));
        registerPanel4.setLayout(new GridLayout(3,1));

        registerPanel2.add(lb_rfnama);
        registerPanel2.add(tx_rfnama);
        registerPanel2.add(lb_rfalamat);
        registerPanel2.add(tx_rfalamat);
        registerPanel2.add(lb_rfusername);
        registerPanel2.add(tx_rfusername);
        registerPanel2.add(lb_rfpw);
        registerPanel2.add(pf_rfpw);
        registerPanel3.add(rb_rfadmin);
        registerPanel3.add(rb_rfkasir);
        registerPanel3.add(rb_rfmanager);
        registerPanel4.add(bt_clear, "CENTER");
        registerPanel4.add(bt_register, "CENTER");
        registerPanel4.add(bt_logout, "CENTER");
        registerPanel1.add(lb_register, BorderLayout.SOUTH);
        registerPanel1.add(registerPanel2);
        registerPanel1.add(registerPanel3);
        registerPanel1.add(registerPanel4);

        // registerPanel1.add(lb_register, BorderLayout.SOUTH);
        // registerPanel1.add(lb_rfnama);
        // registerPanel1.add(tx_rfnama);
        // registerPanel1.add(lb_rfalamat);
        // registerPanel1.add(tx_rfalamat);
        // registerPanel1.add(lb_rfusername);
        // registerPanel1.add(tx_rfusername);
        // registerPanel1.add(lb_rfpw);
        // registerPanel1.add(pf_rfpw);
        // registerPanel1.add(rb_rfkasir);
        // registerPanel1.add(rb_rfmanager);
        // registerPanel1.add(bt_clear, "CENTER");
        // registerPanel1.add(bt_register, "CENTER");
        // registerPanel1.add(bt_logout, "CENTER");

        bt_register.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                bt_registerActionPerformed(evt);
            }
        });

        bt_clear.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    bt_clearActionPerformed(evt);
            }
        });

        bt_logout.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    bt_logoutActionPerformed(evt);
            }
        });
        
        rb_rfadmin.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    rb_rfadminActionPerformed(evt);
            }
        });

        rb_rfkasir.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    rb_rfkasirActionPerformed(evt);
            }
        });

        rb_rfmanager.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    rb_rfmanagerActionPerformed(evt);
            }
        });

        bt_register.addActionListener(this);
        bt_clear.addActionListener(this);
        bt_logout.addActionListener(this);
        rb_rfadmin.addActionListener(this);
        rb_rfkasir.addActionListener(this);
        rb_rfmanager.addActionListener(this);

        registerFrame.add(registerPanel1);
        registerFrame.setLocationRelativeTo(null);
    }

    public void startFrameRegister(){
        registerFrame.setSize(300,400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = registerFrame.getSize();
        registerFrame.setLocation(((screenSize.width - frameSize.width) / 2),
        ((screenSize.height - frameSize.height) / 2));

        // loginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setVisible(true);
    }
    
    void rb_rfadminActionPerformed(java.awt.event.ActionEvent evt){

    }

    void rb_rfkasirActionPerformed(java.awt.event.ActionEvent evt){

    }

    void rb_rfmanagerActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_registerActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_clearActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt){

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bt_register && this.rb_rfkasir.isSelected()){
            if(tx_rfnama.getText().isEmpty() || tx_rfalamat.getText().isEmpty() || tx_rfusername.getText().isEmpty() || pf_rfpw.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Registrasi Gagal, Ada Field Yang Kosong!");
            }else{
                Transaksi transaksi = new Transaksi();
                transaksi.createFolder();
                transaksi.readFile("\\dataKasir.txt");
                transaksi.countLines("\\dataKasir.txt");
                transaksi.addData(tx_rfnama.getText(),tx_rfalamat.getText(),tx_rfusername.getText(),pf_rfpw.getText(),"\\dataKasir.txt");
                tx_rfnama.setText("");
                tx_rfalamat.setText("");
                tx_rfusername.setText("");
                pf_rfpw.setText("");
            }
             // register
        }else if(ae.getSource() == bt_register && this.rb_rfmanager.isSelected()){
            if(tx_rfnama.getText().isEmpty() || tx_rfalamat.getText().isEmpty() || tx_rfusername.getText().isEmpty() || pf_rfpw.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Registrasi Gagal, Ada Field Yang Kosong!");
            }else{
                Transaksi transaksi = new Transaksi();
                transaksi.createFolder();
                transaksi.readFile("\\dataManager.txt");
                transaksi.countLines("\\dataManager.txt");
                transaksi.addData(tx_rfnama.getText(),tx_rfalamat.getText(),tx_rfusername.getText(),pf_rfpw.getText(),"\\dataManager.txt");
                tx_rfnama.setText("");
                tx_rfalamat.setText("");
                tx_rfusername.setText("");
                pf_rfpw.setText("");
            }
        // penjualan
        }else if(ae.getSource() == bt_register && this.rb_rfadmin.isSelected()){
            if(tx_rfnama.getText().isEmpty() || tx_rfalamat.getText().isEmpty() || tx_rfusername.getText().isEmpty() || pf_rfpw.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Registrasi Gagal, Ada Field Yang Kosong!");
            }else{
                Transaksi transaksi = new Transaksi();
                transaksi.createFolder();
                transaksi.readFile("\\dataAdmin.txt");
                transaksi.countLines("\\dataAdmin.txt");
                transaksi.addData(tx_rfnama.getText(),tx_rfalamat.getText(),tx_rfusername.getText(),pf_rfpw.getText(),"\\dataAdmin.txt");
                tx_rfnama.setText("");
                tx_rfalamat.setText("");
                tx_rfusername.setText("");
                pf_rfpw.setText("");
            }
        // penjualan
        }else if(ae.getSource() == bt_clear){
            tx_rfnama.setText("");
            tx_rfalamat.setText("");
            tx_rfusername.setText("");
            pf_rfpw.setText("");
        }else if(ae.getSource() == bt_logout){
            Transaksi transaksi = new Transaksi();
            transaksi.nilai = 0;

            tx_rfnama.setText("");
            tx_rfalamat.setText("");
            tx_rfusername.setText("");
            pf_rfpw.setText("");

            Login login = new Login();
            login.startFrameLogin();
            registerFrame.setVisible(false);
        }
    }
}

class Items extends JFrame implements ActionListener{
    private JFrame itemsFrame;
    private JLabel lb_nama, lb_harga, lb_stok, lb_tgl_masuk, lb_tgl_kadaluarsa, lb_items;
    private JTextField tx_nama, tx_harga, tx_stok, tx_tgl_masuk, tx_tgl_kadaluarsa;
    private JComboBox cb_jenis;
    private JButton bt_save, bt_clear, bt_logout;

    private String jenisBarang, choice = "";

    private String l1[] = {"Pilih Jenis Barang", "Perlengkapan Mandi", "Makanan", "Minuman"};

    public Items(){
        itemsFrame = new JFrame("Input Items");
        lb_nama = new JLabel("Nama Barang :");
        lb_harga = new JLabel("Harga Barang :");
        lb_stok = new JLabel("Jumlah Stok :");
        lb_tgl_masuk = new JLabel("Tanggal Masuk :");
        lb_tgl_kadaluarsa = new JLabel("Tanggal Kadaluarsa :");
        lb_items = new JLabel("Form Input Barang", SwingConstants.CENTER);
        tx_nama = new JTextField(20);
        tx_harga = new JTextField(20);
        tx_stok = new JTextField(20);
        tx_tgl_masuk = new JTextField(20);
        tx_tgl_kadaluarsa = new JTextField(20);
        cb_jenis = new JComboBox(l1);
        bt_save = new JButton("Simpan");
        bt_clear = new JButton("Clear");
        bt_logout = new JButton("Back");

        tx_nama.setEnabled(false);
        tx_harga.setEnabled(false);
        tx_stok.setEnabled(false);
        tx_tgl_masuk.setEnabled(false);
        tx_tgl_kadaluarsa.setEnabled(false);

        JPanel itemsPanel1 = new JPanel();
        JPanel itemsPanel2 = new JPanel();
        JPanel itemsPanel3 = new JPanel();

        itemsPanel1.setLayout(new GridLayout(4,1));
        itemsPanel2.setLayout(new GridLayout(5,2));
        itemsPanel3.setLayout(new GridLayout(3,1));

        itemsPanel2.add(lb_nama);
        itemsPanel2.add(tx_nama);
        itemsPanel2.add(lb_harga);
        itemsPanel2.add(tx_harga);
        itemsPanel2.add(lb_stok);
        itemsPanel2.add(tx_stok);
        itemsPanel2.add(lb_tgl_masuk);
        itemsPanel2.add(tx_tgl_masuk);
        itemsPanel2.add(lb_tgl_kadaluarsa);
        itemsPanel2.add(tx_tgl_kadaluarsa);
        itemsPanel3.add(bt_clear, "CENTER");
        itemsPanel3.add(bt_save, "CENTER");
        itemsPanel3.add(bt_logout, "CENTER");
        itemsPanel1.add(lb_items, BorderLayout.SOUTH);
        itemsPanel1.add(cb_jenis);
        itemsPanel1.add(itemsPanel2);
        itemsPanel1.add(itemsPanel3);

        // itemsPanel1.add(lb_items, BorderLayout.SOUTH);
        // itemsPanel1.add(cb_jenis);
        // itemsPanel1.add(lb_nama);
        // itemsPanel1.add(tx_nama);
        // itemsPanel1.add(lb_harga);
        // itemsPanel1.add(tx_harga);
        // itemsPanel1.add(lb_stok);
        // itemsPanel1.add(tx_stok);
        // itemsPanel1.add(lb_tgl_masuk);
        // itemsPanel1.add(tx_tgl_masuk);
        // itemsPanel1.add(lb_tgl_kadaluarsa);
        // itemsPanel1.add(tx_tgl_kadaluarsa);
        // itemsPanel1.add(bt_clear, "CENTER");
        // itemsPanel1.add(bt_save, "CENTER");
        // itemsPanel1.add(bt_logout, "CENTER");


        cb_jenis.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                cb_jenisActionPerformed(evt);
            }
        });

        bt_save.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                bt_saveActionPerformed(evt);
            }
        });

        bt_clear.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                bt_clearActionPerformed(evt);
            }
        });

        bt_logout.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                bt_logoutActionPerformed(evt);
            }
        });

        cb_jenis.addActionListener(this);
        bt_save.addActionListener(this);
        bt_clear.addActionListener(this);
        bt_logout.addActionListener(this);

        itemsFrame.add(itemsPanel1);
        itemsFrame.setLocationRelativeTo(null);

    }

    public void startFrameItems(){
        itemsFrame.setSize(300,400);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = itemsFrame.getSize();
        itemsFrame.setLocation(((screenSize.width - frameSize.width) / 2),
        ((screenSize.height - frameSize.height) / 2));

        // loginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        itemsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemsFrame.setVisible(true);
    }

    private void cb_jenisActionPerformed(java.awt.event.ActionEvent evt){
        choice = (String) cb_jenis.getSelectedItem();
        switch(choice){
            case "Pilih Jenis Barang":
                tx_nama.setEnabled(false);
                tx_harga.setEnabled(false);
                tx_stok.setEnabled(false);
                tx_tgl_masuk.setEnabled(false);
                tx_tgl_kadaluarsa.setEnabled(false);
                break;

            case "Perlengkapan Mandi":
                tx_nama.setEnabled(true);
                tx_harga.setEnabled(true);
                tx_stok.setEnabled(true);
                tx_tgl_masuk.setEnabled(true);
                tx_tgl_kadaluarsa.setEnabled(false);
                break;

            case "Makanan":
                tx_nama.setEnabled(true);
                tx_harga.setEnabled(true);
                tx_stok.setEnabled(true);
                tx_tgl_masuk.setEnabled(true);
                tx_tgl_kadaluarsa.setEnabled(true);
                break;

            case "Minuman":
                tx_nama.setEnabled(true);
                tx_harga.setEnabled(true);
                tx_stok.setEnabled(true);
                tx_tgl_masuk.setEnabled(true);
                tx_tgl_kadaluarsa.setEnabled(true);
                break;
        }
    }

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_clearActionPerformed(java.awt.event.ActionEvent evt){

    }

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt){

    }

    public void actionPerformed(ActionEvent ae){
        Transaksi transaksi = new Transaksi();
        transaksi.createFolder();
        transaksi.readFile("\\dataItems.txt");
        transaksi.countLines("\\dataItems.txt");

        if(ae.getSource() == bt_save){
            if(tx_nama.getText().isEmpty() || tx_harga.getText().isEmpty() || tx_stok.getText().isEmpty() || tx_tgl_masuk.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data, Ada Field Yang Kosong!");
            }else if(transaksi.checkData2(tx_nama.getText(), "\\dataItems.txt") == 1){
                // JOptionPane.showMessageDialog(null, "Gagal Menyimpan!");
            }else{
                int hargaBarang = Integer.parseInt(tx_harga.getText());
                int stokBarang = Integer.parseInt(tx_stok.getText());
                transaksi.addData2(choice, tx_nama.getText(), hargaBarang, stokBarang, tx_tgl_masuk.getText(), tx_tgl_kadaluarsa.getText(), "\\dataItems.txt");
                cb_jenis.setSelectedItem("Pilih Jenis Barang");
                tx_nama.setText("");
                tx_harga.setText("");
                tx_stok.setText("");
                tx_tgl_masuk.setText("");
                tx_tgl_kadaluarsa.setText("");
                JOptionPane.showMessageDialog(null, "Data Telah Tersimpan!");
            }
        }else if(ae.getSource() == bt_clear){
            tx_nama.setText("");
            tx_harga.setText("");
            tx_stok.setText("");
            tx_tgl_masuk.setText("");
            tx_tgl_kadaluarsa.setText("");
        }else if(ae.getSource() == bt_logout){			
            tx_nama.setText("");
            tx_harga.setText("");
            tx_stok.setText("");
            tx_tgl_masuk.setText("");
            tx_tgl_kadaluarsa.setText("");

            ManagerMenu mMenu = new ManagerMenu();
            mMenu.setVisible(true);
            itemsFrame.setVisible(false);
        }
    }
}

class RegisAdmin extends JFrame implements ActionListener{
    private JFrame regisAdminFrame;
    private JLabel lb_admin, lb_nama, lb_alamat, lb_username, lb_pw;
    private JTextField tx_nama, tx_alamat, tx_username;
    private JPasswordField pf_pw;
    private JButton bt_register;

    public RegisAdmin(){
        regisAdminFrame = new JFrame("Register Admin");
        lb_admin = new JLabel("Register Admin", SwingConstants.CENTER);
        lb_nama = new JLabel("Nama :");
        lb_alamat = new JLabel("Alamat :");
        lb_username = new JLabel("Username :");
        lb_pw = new JLabel("Password :");
        tx_nama = new JTextField(20);
        tx_alamat = new JTextField(20);
        tx_username = new JTextField(20);
        pf_pw = new JPasswordField(20);
        bt_register = new JButton("Register");

        JPanel regisAdminPane1 = new JPanel();
        JPanel regisAdminPane2 = new JPanel();
        JPanel regisAdminPane3 = new JPanel();

        regisAdminPane1.setLayout(new GridLayout(3, 1));
        regisAdminPane2.setLayout(new GridLayout(4, 2));
        regisAdminPane3.setLayout(new GridLayout(1, 1));

        regisAdminPane2.add(lb_nama);
        regisAdminPane2.add(tx_nama);
        regisAdminPane2.add(lb_alamat);
        regisAdminPane2.add(tx_alamat);
        regisAdminPane2.add(lb_username);
        regisAdminPane2.add(tx_username);
        regisAdminPane2.add(lb_pw);
        regisAdminPane2.add(pf_pw);
        regisAdminPane3.add(bt_register, "CENTER");
        regisAdminPane1.add(lb_admin, BorderLayout.SOUTH);
        regisAdminPane1.add(regisAdminPane2);
        regisAdminPane1.add(regisAdminPane3);


        bt_register.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                    bt_registerActionPerformed(evt);
            }
        });

        this.bt_register.addActionListener(this);

        regisAdminFrame.add(regisAdminPane1);
        regisAdminFrame.setLocationRelativeTo(null);
    }

    public void startFrameRegisAdmin(){
        regisAdminFrame.setSize(300,300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = regisAdminFrame.getSize();
        regisAdminFrame.setLocation(((screenSize.width - frameSize.width) / 2),
        ((screenSize.height - frameSize.height) / 2));

        // loginFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,40));
        regisAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regisAdminFrame.setVisible(true);
    }

    private void bt_registerActionPerformed(java.awt.event.ActionEvent evt){

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == bt_register){
            if(tx_nama.getText().isEmpty() || tx_alamat.getText().isEmpty() || tx_username.getText().isEmpty() || pf_pw.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Registrasi Gagal, Ada Field Yang Kosong!");
            }else{
                Transaksi transaksi = new Transaksi();
                transaksi.createFolder();
                transaksi.readFile("\\dataAdmin.txt");
                transaksi.countLines("\\dataAdmin.txt");
                transaksi.addData(tx_nama.getText(),tx_alamat.getText(),tx_username.getText(),pf_pw.getText(),"\\dataAdmin.txt");
                tx_nama.setText("");
                tx_alamat.setText("");
                tx_username.setText("");
                pf_pw.setText("");

                Login login = new Login();
                login.startFrameLogin();
                regisAdminFrame.setVisible(false);
            }
        }
    }
}