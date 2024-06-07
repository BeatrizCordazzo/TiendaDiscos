/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiendadiscos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Desktop;
import java.io.IOException;
import java.time.LocalDate;
import modelo.Cliente;
import modelo.ClienteApp;
import modelo.Datos;
import modelo.Detalle;
import modelo.Discos;
import modelo.DiscosApp;
import modelo.Eventos;
import modelo.Pedidos;
import modelo.PedidosApp;
import modelo.Proveedor;
import modelo.ProveedorApp;
import org.apache.commons.collections4.map.FixedSizeSortedMap;
import reportes.Excel;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.login;

/**
 *
 * @author DAW
 */
public class Tienda extends javax.swing.JFrame {

    Cliente cl = new Cliente();
    ClienteApp client = new ClienteApp();
    Proveedor pr = new Proveedor();
    ProveedorApp prDao = new ProveedorApp();
    Discos dis = new Discos();
    DiscosApp disDao = new DiscosApp();
    Pedidos p = new Pedidos();
    PedidosApp pDao = new PedidosApp();
    Detalle Dv = new Detalle();
    Datos dat = new Datos();
    Eventos even = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Totalpagar = 0.00;

    /**
     * Creates new form Tienda
     */
    public Tienda() {
        initComponents();

    }

    public Tienda(login priv) {
        initComponents();
        this.setLocationRelativeTo(null);
        idClienteTxt.setVisible(false);
        idVentaTxt.setVisible(false);
        idProvTxt.setVisible(false);
        iddiscTxt.setVisible(false);
        idDiscTxt.setVisible(false);
        disDao.ConsultarProveedor(provDiscCbx);
        idDatosTxt.setVisible(false);
        telCVTxt.setVisible(false);
        direcCVTxt.setVisible(false);
        if (priv.getCargo().equals("Vendedor")) {
            ProveedorBtn.setEnabled(false);
            ConfigBtn.setEnabled(false);
            vendedorLabel.setText(priv.getNombre());
        } else {
            vendedorLabel.setText(priv.getNombre());
        }
    }

    public void ListarCliente() {
        List<Cliente> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) tableCliente.getModel();
        Object[] obj = new Object[5];
        for (int i = 0; i < ListarCl.size(); i++) {
            obj[0] = ListarCl.get(i).getId();
            obj[1] = ListarCl.get(i).getDni();
            obj[2] = ListarCl.get(i).getNombre();
            obj[3] = ListarCl.get(i).getTelefono();
            obj[4] = ListarCl.get(i).getDireccion();
            modelo.addRow(obj);
        }
        tableCliente.setModel(modelo);
    }

    public void ListarProveedor() {
        List<Proveedor> ListarPr = prDao.ListarProveedor();
        modelo = (DefaultTableModel) tableProveedor.getModel();
        Object[] obj = new Object[5];
        for (int i = 0; i < ListarPr.size(); i++) {
            obj[0] = ListarPr.get(i).getId();
            obj[1] = ListarPr.get(i).getRuc();
            obj[2] = ListarPr.get(i).getNombre();
            obj[3] = ListarPr.get(i).getTelefono();
            obj[4] = ListarPr.get(i).getDireccion();
            modelo.addRow(obj);
        }
        tableProveedor.setModel(modelo);
    }

    public void ListarDiscos() {
        List<Discos> ListarDis = disDao.ListarDiscos();
        modelo = (DefaultTableModel) tableDisco.getModel();
        Object[] obj = new Object[6];
        for (int i = 0; i < ListarDis.size(); i++) {
            obj[0] = ListarDis.get(i).getId();
            obj[1] = ListarDis.get(i).getCodigo();
            obj[2] = ListarDis.get(i).getNombre();
            obj[3] = ListarDis.get(i).getProveedor();
            obj[4] = ListarDis.get(i).getStock();
            obj[5] = ListarDis.get(i).getPrecio();
            modelo.addRow(obj);
        }
        tableProveedor.setModel(modelo);
    }

    public void ListarPedidos() {
        List<Pedidos> ListarPedido = pDao.ListarPedidos();
        modelo = (DefaultTableModel) tablePdfVentas.getModel();
        Object[] obj = new Object[4];
        for (int i = 0; i < ListarPedido.size(); i++) {
            obj[0] = ListarPedido.get(i).getId();
            obj[1] = ListarPedido.get(i).getCliente();
            obj[2] = ListarPedido.get(i).getVendedor();
            obj[3] = ListarPedido.get(i).getTotal();
            modelo.addRow(obj);
        }
        tablePdfVentas.setModel(modelo);
    }

    public void ListarDatos() {
        dat = disDao.BuscarDatos();
        idDatosTxt.setText("" + dat.getId());
        rucDatosTxt.setText("" + dat.getRuc());
        nomDatosTxt.setText("" + dat.getNombre());
        telDatosTxt.setText("" + dat.getTelefono());
        dirDatosTxt.setText("" + dat.getDireccion());
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
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

        jPanel5 = new javax.swing.JPanel();
        NuevaVentaBtn = new javax.swing.JButton();
        ProveedorBtn = new javax.swing.JButton();
        ConfigBtn = new javax.swing.JButton();
        DiscosBtn = new javax.swing.JButton();
        ClientesBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        VentasBtn = new javax.swing.JButton();
        vendedorLabel = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ElimBtn = new javax.swing.JButton();
        codVentaTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableVenta = new javax.swing.JTable();
        descVentaTxt = new javax.swing.JTextField();
        cantVentaTxt = new javax.swing.JTextField();
        stockDispTxt = new javax.swing.JTextField();
        precVentaTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dniVentaTxt = new javax.swing.JTextField();
        facturaBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        nomCliVentaTxt = new javax.swing.JTextField();
        totalLabel = new javax.swing.JTextField();
        telCVTxt = new javax.swing.JTextField();
        direcCVTxt = new javax.swing.JTextField();
        iddiscTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        dniCliTxt = new javax.swing.JTextField();
        nomCliTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        telCliTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dirCliTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCliente = new javax.swing.JTable();
        guardarCTxt = new javax.swing.JButton();
        editarCBtn = new javax.swing.JButton();
        ElimCBtn = new javax.swing.JButton();
        nuevoCBtn = new javax.swing.JButton();
        idClienteTxt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rucProvTxt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProveedor = new javax.swing.JTable();
        nomProvTxt = new javax.swing.JTextField();
        telProvTxt = new javax.swing.JTextField();
        dirProvTxt = new javax.swing.JTextField();
        guardarProvBtn = new javax.swing.JButton();
        editProvBtn = new javax.swing.JButton();
        elimProvBtn = new javax.swing.JButton();
        nuevoProvBtn = new javax.swing.JButton();
        idProvTxt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        provDiscCbx = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        codDiscTxt = new javax.swing.JTextField();
        descDiscTxt = new javax.swing.JTextField();
        cantDiscTxt = new javax.swing.JTextField();
        precDiscTxt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDisco = new javax.swing.JTable();
        guardarDiscBtn = new javax.swing.JButton();
        elimDiscBtn = new javax.swing.JButton();
        nuevoDiscBtn = new javax.swing.JButton();
        editDiscBtn = new javax.swing.JButton();
        excelDiscBtn = new javax.swing.JButton();
        idDiscTxt = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablePdfVentas = new javax.swing.JTable();
        pdfVentasBtn = new javax.swing.JButton();
        idVentaTxt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rucDatosTxt = new javax.swing.JTextField();
        nomDatosTxt = new javax.swing.JTextField();
        telDatosTxt = new javax.swing.JTextField();
        dirDatosTxt = new javax.swing.JTextField();
        actDatosBtn = new javax.swing.JButton();
        idDatosTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        NuevaVentaBtn.setBackground(new java.awt.Color(255, 255, 255));
        NuevaVentaBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        NuevaVentaBtn.setForeground(new java.awt.Color(0, 0, 0));
        NuevaVentaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/metodo-de-pago.png"))); // NOI18N
        NuevaVentaBtn.setText("Nueva Venta");
        NuevaVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevaVentaBtnActionPerformed(evt);
            }
        });

        ProveedorBtn.setBackground(new java.awt.Color(255, 255, 255));
        ProveedorBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ProveedorBtn.setForeground(new java.awt.Color(0, 0, 0));
        ProveedorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/mensajero.png"))); // NOI18N
        ProveedorBtn.setText("Proveedor");
        ProveedorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedorBtnActionPerformed(evt);
            }
        });

        ConfigBtn.setBackground(new java.awt.Color(255, 255, 255));
        ConfigBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ConfigBtn.setForeground(new java.awt.Color(0, 0, 0));
        ConfigBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/empresa.png"))); // NOI18N
        ConfigBtn.setText("Empresa");
        ConfigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfigBtnActionPerformed(evt);
            }
        });

        DiscosBtn.setBackground(new java.awt.Color(255, 255, 255));
        DiscosBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        DiscosBtn.setForeground(new java.awt.Color(0, 0, 0));
        DiscosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/disco-de-musica-con-detalles-en-blanco.png"))); // NOI18N
        DiscosBtn.setText("Discos");
        DiscosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiscosBtnActionPerformed(evt);
            }
        });

        ClientesBtn.setBackground(new java.awt.Color(255, 255, 255));
        ClientesBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ClientesBtn.setForeground(new java.awt.Color(0, 0, 0));
        ClientesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/objetivo.png"))); // NOI18N
        ClientesBtn.setText("Clientes");
        ClientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesBtnActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/tocadiscospeq.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Spin City Records");

        VentasBtn.setBackground(new java.awt.Color(255, 255, 255));
        VentasBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        VentasBtn.setForeground(new java.awt.Color(0, 0, 0));
        VentasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/ventas.png"))); // NOI18N
        VentasBtn.setText("Ventas");
        VentasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentasBtnActionPerformed(evt);
            }
        });

        vendedorLabel.setForeground(new java.awt.Color(0, 0, 0));
        vendedorLabel.setText("Spin City Records");

        jLabel31.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Usuario:");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NuevaVentaBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ClientesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(ConfigBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(VentasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DiscosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(ProveedorBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vendedorLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(NuevaVentaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ClientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ProveedorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DiscosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(VentasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ConfigBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendedorLabel)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 670));

        jPanel1.setBackground(new java.awt.Color(90, 163, 206));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/venta-internet.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VENTAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 890, 140));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(null);
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTabbedPane1AncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Código");

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Descripción");

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Cantidad");

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Precio");

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Stock");

        ElimBtn.setBackground(new java.awt.Color(255, 255, 255));
        ElimBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/borrar.png"))); // NOI18N
        ElimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElimBtnActionPerformed(evt);
            }
        });

        codVentaTxt.setBackground(new java.awt.Color(204, 204, 204));
        codVentaTxt.setForeground(new java.awt.Color(0, 0, 0));
        codVentaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codVentaTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codVentaTxtKeyTyped(evt);
            }
        });

        tableVenta.setBackground(new java.awt.Color(255, 255, 255));
        tableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        tableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVentaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableVenta);
        if (tableVenta.getColumnModel().getColumnCount() > 0) {
            tableVenta.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableVenta.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        descVentaTxt.setBackground(new java.awt.Color(204, 204, 204));
        descVentaTxt.setForeground(new java.awt.Color(0, 0, 0));
        descVentaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descVentaTxtKeyTyped(evt);
            }
        });

        cantVentaTxt.setBackground(new java.awt.Color(204, 204, 204));
        cantVentaTxt.setForeground(new java.awt.Color(0, 0, 0));
        cantVentaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantVentaTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantVentaTxtKeyTyped(evt);
            }
        });

        stockDispTxt.setBackground(new java.awt.Color(204, 204, 204));
        stockDispTxt.setForeground(new java.awt.Color(0, 0, 0));

        precVentaTxt.setEditable(false);
        precVentaTxt.setBackground(new java.awt.Color(204, 204, 204));
        precVentaTxt.setForeground(new java.awt.Color(0, 0, 0));
        precVentaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precVentaTxtKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("DNI/RUC");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nombre");

        dniVentaTxt.setBackground(new java.awt.Color(204, 204, 204));
        dniVentaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dniVentaTxtKeyPressed(evt);
            }
        });

        facturaBtn.setBackground(new java.awt.Color(255, 255, 255));
        facturaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/factura.png"))); // NOI18N
        facturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturaBtnActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/iconos-de-dinero.png"))); // NOI18N
        jLabel7.setText("Total  a Pagar:");

        nomCliVentaTxt.setEditable(false);
        nomCliVentaTxt.setBackground(new java.awt.Color(204, 204, 204));

        totalLabel.setBackground(new java.awt.Color(204, 204, 204));

        telCVTxt.setBackground(new java.awt.Color(255, 255, 255));

        direcCVTxt.setBackground(new java.awt.Color(255, 255, 255));

        iddiscTxt.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10))
                    .addComponent(codVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cantVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(precVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockDispTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(ElimBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iddiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dniVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(nomCliVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(telCVTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(direcCVTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(facturaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(iddiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ElimBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(descVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockDispTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dniVentaTxt)
                            .addComponent(nomCliVentaTxt)
                            .addComponent(telCVTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direcCVTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(facturaBtn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 897, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("DNI:");

        dniCliTxt.setBackground(new java.awt.Color(204, 204, 204));
        dniCliTxt.setForeground(new java.awt.Color(0, 0, 0));

        nomCliTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomCliTxt.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nombre:");

        telCliTxt.setBackground(new java.awt.Color(204, 204, 204));
        telCliTxt.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Teléfono:");

        dirCliTxt.setBackground(new java.awt.Color(204, 204, 204));
        dirCliTxt.setForeground(new java.awt.Color(0, 0, 0));

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Dirección:");

        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        tableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCliente);
        if (tableCliente.getColumnModel().getColumnCount() > 0) {
            tableCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableCliente.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        guardarCTxt.setBackground(new java.awt.Color(255, 255, 255));
        guardarCTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/salvar.png"))); // NOI18N
        guardarCTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarCTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCTxtActionPerformed(evt);
            }
        });

        editarCBtn.setBackground(new java.awt.Color(255, 255, 255));
        editarCBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/actualizar.png"))); // NOI18N
        editarCBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editarCBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCBtnActionPerformed(evt);
            }
        });

        ElimCBtn.setBackground(new java.awt.Color(255, 255, 255));
        ElimCBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/borrar.png"))); // NOI18N
        ElimCBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ElimCBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElimCBtnActionPerformed(evt);
            }
        });

        nuevoCBtn.setBackground(new java.awt.Color(255, 255, 255));
        nuevoCBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/nuevo-archivo.png"))); // NOI18N
        nuevoCBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevoCBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoCBtnActionPerformed(evt);
            }
        });

        idClienteTxt.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardarCTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editarCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nuevoCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ElimCBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(idClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dniCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dirCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dniCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(telCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(dirCliTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(idClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoCBtn)
                            .addComponent(editarCBtn)
                            .addComponent(ElimCBtn)
                            .addComponent(guardarCTxt))
                        .addGap(0, 26, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("RUC:");

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nombre:");

        jLabel19.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Teléfono:");

        jLabel20.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Dirección:");

        rucProvTxt.setBackground(new java.awt.Color(204, 204, 204));
        rucProvTxt.setForeground(new java.awt.Color(0, 0, 0));

        tableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        tableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProveedor);
        if (tableProveedor.getColumnModel().getColumnCount() > 0) {
            tableProveedor.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableProveedor.getColumnModel().getColumn(1).setPreferredWidth(40);
            tableProveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableProveedor.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableProveedor.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        nomProvTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomProvTxt.setForeground(new java.awt.Color(0, 0, 0));

        telProvTxt.setBackground(new java.awt.Color(204, 204, 204));
        telProvTxt.setForeground(new java.awt.Color(0, 0, 0));

        dirProvTxt.setBackground(new java.awt.Color(204, 204, 204));
        dirProvTxt.setForeground(new java.awt.Color(0, 0, 0));

        guardarProvBtn.setBackground(new java.awt.Color(255, 255, 255));
        guardarProvBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/salvar.png"))); // NOI18N
        guardarProvBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarProvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProvBtnActionPerformed(evt);
            }
        });

        editProvBtn.setBackground(new java.awt.Color(255, 255, 255));
        editProvBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/actualizar.png"))); // NOI18N
        editProvBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editProvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProvBtnActionPerformed(evt);
            }
        });

        elimProvBtn.setBackground(new java.awt.Color(255, 255, 255));
        elimProvBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/borrar.png"))); // NOI18N
        elimProvBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        elimProvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elimProvBtnActionPerformed(evt);
            }
        });

        nuevoProvBtn.setBackground(new java.awt.Color(255, 255, 255));
        nuevoProvBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/nuevo-archivo.png"))); // NOI18N
        nuevoProvBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevoProvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProvBtnActionPerformed(evt);
            }
        });

        idProvTxt.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(idProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telProvTxt))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rucProvTxt)))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomProvTxt))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dirProvTxt)))
                .addGap(129, 129, 129))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(guardarProvBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(editProvBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(nuevoProvBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(elimProvBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(nomProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(rucProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(telProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dirProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(elimProvBtn)
                    .addComponent(nuevoProvBtn)
                    .addComponent(editProvBtn)
                    .addComponent(guardarProvBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idProvTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        provDiscCbx.setBackground(new java.awt.Color(255, 255, 255));
        provDiscCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provDiscCbxActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Precio:");

        jLabel22.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Cantidad:");

        jLabel23.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Descripción:");

        jLabel24.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Código:");

        codDiscTxt.setBackground(new java.awt.Color(204, 204, 204));
        codDiscTxt.setForeground(new java.awt.Color(0, 0, 0));
        codDiscTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codDiscTxtActionPerformed(evt);
            }
        });

        descDiscTxt.setBackground(new java.awt.Color(204, 204, 204));
        descDiscTxt.setForeground(new java.awt.Color(0, 0, 0));

        cantDiscTxt.setBackground(new java.awt.Color(204, 204, 204));
        cantDiscTxt.setForeground(new java.awt.Color(0, 0, 0));

        precDiscTxt.setBackground(new java.awt.Color(204, 204, 204));
        precDiscTxt.setForeground(new java.awt.Color(0, 0, 0));
        precDiscTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precDiscTxtKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Proveedor:");

        tableDisco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "DESCRIPCIÓN", "PROVEEDOR", "STOCK", "PRECIO"
            }
        ));
        tableDisco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDiscoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableDisco);
        if (tableDisco.getColumnModel().getColumnCount() > 0) {
            tableDisco.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableDisco.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableDisco.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableDisco.getColumnModel().getColumn(3).setPreferredWidth(60);
            tableDisco.getColumnModel().getColumn(4).setPreferredWidth(40);
            tableDisco.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        guardarDiscBtn.setBackground(new java.awt.Color(255, 255, 255));
        guardarDiscBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/salvar.png"))); // NOI18N
        guardarDiscBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarDiscBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarDiscBtnActionPerformed(evt);
            }
        });

        elimDiscBtn.setBackground(new java.awt.Color(255, 255, 255));
        elimDiscBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/borrar.png"))); // NOI18N
        elimDiscBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        elimDiscBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elimDiscBtnActionPerformed(evt);
            }
        });

        nuevoDiscBtn.setBackground(new java.awt.Color(255, 255, 255));
        nuevoDiscBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/nuevo-archivo.png"))); // NOI18N
        nuevoDiscBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevoDiscBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoDiscBtnActionPerformed(evt);
            }
        });

        editDiscBtn.setBackground(new java.awt.Color(255, 255, 255));
        editDiscBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/actualizar.png"))); // NOI18N
        editDiscBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editDiscBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDiscBtnActionPerformed(evt);
            }
        });

        excelDiscBtn.setBackground(new java.awt.Color(255, 255, 255));
        excelDiscBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/sobresalir.png"))); // NOI18N
        excelDiscBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excelDiscBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelDiscBtnActionPerformed(evt);
            }
        });

        idDiscTxt.setBackground(new java.awt.Color(255, 255, 255));
        idDiscTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idDiscTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idDiscTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardarDiscBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(editDiscBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(nuevoDiscBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(excelDiscBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(elimDiscBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantDiscTxt))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precDiscTxt))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(provDiscCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(codDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(descDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cantDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(precDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(provDiscCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoDiscBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(excelDiscBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editDiscBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guardarDiscBtn, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(elimDiscBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idDiscTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tablePdfVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ));
        tablePdfVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePdfVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablePdfVentas);
        if (tablePdfVentas.getColumnModel().getColumnCount() > 0) {
            tablePdfVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablePdfVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablePdfVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            tablePdfVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        pdfVentasBtn.setBackground(new java.awt.Color(255, 255, 255));
        pdfVentasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/archivo-pdf.png"))); // NOI18N
        pdfVentasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfVentasBtnActionPerformed(evt);
            }
        });

        idVentaTxt.setBackground(new java.awt.Color(255, 255, 255));
        idVentaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idVentaTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(idVentaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pdfVentasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idVentaTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(pdfVentasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        jTabbedPane1.addTab("tab5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("DATOS DE LA EMPRESA");

        jLabel27.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("RUC:");

        jLabel28.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Nombre:");

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Teléfono:");

        jLabel30.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Dirección:");

        rucDatosTxt.setBackground(new java.awt.Color(204, 204, 204));
        rucDatosTxt.setForeground(new java.awt.Color(0, 0, 0));
        rucDatosTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rucDatosTxtActionPerformed(evt);
            }
        });

        nomDatosTxt.setBackground(new java.awt.Color(204, 204, 204));
        nomDatosTxt.setForeground(new java.awt.Color(0, 0, 0));

        telDatosTxt.setBackground(new java.awt.Color(204, 204, 204));
        telDatosTxt.setForeground(new java.awt.Color(0, 0, 0));

        dirDatosTxt.setBackground(new java.awt.Color(204, 204, 204));
        dirDatosTxt.setForeground(new java.awt.Color(0, 0, 0));

        actDatosBtn.setBackground(new java.awt.Color(255, 255, 255));
        actDatosBtn.setForeground(new java.awt.Color(0, 0, 0));
        actDatosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/misimg/actualizar.png"))); // NOI18N
        actDatosBtn.setText("ACTUALIZAR");
        actDatosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actDatosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actDatosBtnActionPerformed(evt);
            }
        });

        idDatosTxt.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telDatosTxt))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rucDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dirDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel26))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(actDatosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(idDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel26)
                .addGap(72, 72, 72)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(rucDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(nomDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(telDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dirDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)))
                .addGap(65, 65, 65)
                .addComponent(actDatosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(idDatosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab6", jPanel8);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 890, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void facturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturaBtnActionPerformed
        if (tableVenta.getRowCount() > 0) {
            if (!"".equals(nomCliVentaTxt.getText())) {
                RegistrarPedido();
                RegistrarDetalle();
                ActualizarStock();
                pdf();
                LimpiarTableVenta();
                LimpiarClienteVenta();
            } else {
                JOptionPane.showMessageDialog(null, "Busque un cliente!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la venta!");
        }

    }//GEN-LAST:event_facturaBtnActionPerformed

    private void idDiscTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idDiscTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idDiscTxtActionPerformed

    private void guardarCTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCTxtActionPerformed
        if (!"".equals(dniCliTxt.getText()) || !"".equals(nomCliTxt.getText()) || !"".equals(telCliTxt.getText()) || !"".equals(dirCliTxt.getText())) {
            cl.setDni(dniCliTxt.getText());
            cl.setNombre(nomCliTxt.getText());
            cl.setTelefono(telCliTxt.getText());
            cl.setDireccion(dirCliTxt.getText());
            client.RegistarCliente(cl);
            LimpiarTable();
            LimpiarCliente();
            ListarCliente();
            JOptionPane.showMessageDialog(null, "Cliente Registrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos!");
        }
    }//GEN-LAST:event_guardarCTxtActionPerformed

    private void ClientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesBtnActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        LimpiarTable();
        ListarCliente();
    }//GEN-LAST:event_ClientesBtnActionPerformed

    private void tableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClienteMouseClicked
        int fila = tableCliente.rowAtPoint(evt.getPoint());
        idClienteTxt.setText(tableCliente.getValueAt(fila, 0).toString());
        dniCliTxt.setText(tableCliente.getValueAt(fila, 1).toString());
        nomCliTxt.setText(tableCliente.getValueAt(fila, 2).toString());
        telCliTxt.setText(tableCliente.getValueAt(fila, 3).toString());
        dirCliTxt.setText(tableCliente.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tableClienteMouseClicked

    private void ElimCBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElimCBtnActionPerformed
        if (!"".equals(idClienteTxt.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres borrar la línea?");
            if (pregunta == 0) {
                int id = Integer.parseInt(idClienteTxt.getText());
                client.EliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            }
        }
    }//GEN-LAST:event_ElimCBtnActionPerformed

    private void editarCBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCBtnActionPerformed
        if ("".equals(idClienteTxt.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila!");
        } else {
            if (!"".equals(dniCliTxt.getText()) || !"".equals(nomCliTxt.getText()) || !"".equals(telCliTxt.getText())) {
                cl.setDni(dniCliTxt.getText());
                cl.setNombre(nomCliTxt.getText());
                cl.setTelefono(telCliTxt.getText());
                cl.setDireccion(dirCliTxt.getText());
                cl.setId(Integer.parseInt(idClienteTxt.getText()));
                client.ModificarCliente(cl);
                JOptionPane.showMessageDialog(null, "Cliente Actualizado!");
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Los campos están vacíos!");
            }
        }
    }//GEN-LAST:event_editarCBtnActionPerformed

    private void nuevoCBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoCBtnActionPerformed
        LimpiarCliente();
    }//GEN-LAST:event_nuevoCBtnActionPerformed

    private void guardarProvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProvBtnActionPerformed
        if (!"".equals(rucProvTxt.getText()) || !"".equals(nomProvTxt.getText()) || !"".equals(telProvTxt.getText()) || !"".equals(dirProvTxt.getText())) {
            pr.setRuc(rucProvTxt.getText());
            pr.setNombre(nomProvTxt.getText());
            pr.setTelefono(telProvTxt.getText());
            pr.setDireccion(dirProvTxt.getText());
            prDao.RegistrarProveedor(pr);
            JOptionPane.showMessageDialog(null, "Proveedor Resgistrado!");
            LimpiarTable();
            ListarProveedor();
            LimpiarProveedor();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos!");
        }
    }//GEN-LAST:event_guardarProvBtnActionPerformed

    private void ProveedorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedorBtnActionPerformed
        LimpiarTable();
        ListarProveedor();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_ProveedorBtnActionPerformed

    private void tableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProveedorMouseClicked
        int fila = tableProveedor.rowAtPoint(evt.getPoint());
        idProvTxt.setText(tableProveedor.getValueAt(fila, 0).toString());
        rucProvTxt.setText(tableProveedor.getValueAt(fila, 1).toString());
        nomProvTxt.setText(tableProveedor.getValueAt(fila, 2).toString());
        telProvTxt.setText(tableProveedor.getValueAt(fila, 3).toString());
        dirProvTxt.setText(tableProveedor.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tableProveedorMouseClicked

    private void elimProvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimProvBtnActionPerformed
        if (!"".equals(idProvTxt.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(idProvTxt.getText());
                prDao.EliminarProveedor(id);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila!");
        }
    }//GEN-LAST:event_elimProvBtnActionPerformed

    private void editProvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProvBtnActionPerformed
        if ("".equals(idProvTxt.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila!");
        } else {
            if (!"".equals(rucProvTxt.getText()) || !"".equals(nomProvTxt.getText()) || !"".equals(nomProvTxt.getText()) || !"".equals(dirProvTxt.getText())) {
                pr.setRuc(rucProvTxt.getText());
                pr.setNombre(nomProvTxt.getText());
                pr.setTelefono(telProvTxt.getText());
                pr.setDireccion(dirProvTxt.getText());
                pr.setId(Integer.parseInt(idProvTxt.getText()));
                prDao.ModificarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado!");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        }
    }//GEN-LAST:event_editProvBtnActionPerformed

    private void nuevoProvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProvBtnActionPerformed
        LimpiarProveedor();
    }//GEN-LAST:event_nuevoProvBtnActionPerformed

    private void guardarDiscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarDiscBtnActionPerformed
        if (!"".equals(codDiscTxt.getText()) || !"".equals(descDiscTxt.getText()) || !"".equals(provDiscCbx.getSelectedItem()) || !"".equals(cantDiscTxt.getText()) || !"".equals(precDiscTxt.getText())) {
            dis.setCodigo(codDiscTxt.getText());
            dis.setNombre(descDiscTxt.getText());
            dis.setProveedor(provDiscCbx.getSelectedItem().toString());
            dis.setStock(Integer.parseInt(cantDiscTxt.getText()));
            dis.setPrecio(Double.parseDouble(precDiscTxt.getText()));
            disDao.RegistrarDiscos(dis);
            JOptionPane.showMessageDialog(null, "Disco Resgistrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos!");
        }
    }//GEN-LAST:event_guardarDiscBtnActionPerformed

    private void DiscosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiscosBtnActionPerformed
        LimpiarTable();
        ListarDiscos();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_DiscosBtnActionPerformed

    private void tableDiscoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDiscoMouseClicked
        int fila = tableDisco.rowAtPoint(evt.getPoint());
        idDiscTxt.setText(tableDisco.getValueAt(fila, 0).toString());
        codDiscTxt.setText(tableDisco.getValueAt(fila, 1).toString());
        descDiscTxt.setText(tableDisco.getValueAt(fila, 2).toString());
        provDiscCbx.setSelectedItem(tableDisco.getValueAt(fila, 3).toString());
        cantDiscTxt.setText(tableDisco.getValueAt(fila, 4).toString());
        precDiscTxt.setText(tableDisco.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_tableDiscoMouseClicked

    private void elimDiscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimDiscBtnActionPerformed
        if (!"".equals(idDiscTxt.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres borrar la línea?");
            if (pregunta == 0) {
                int id = Integer.parseInt(idDiscTxt.getText());
                disDao.EliminarDiscos(id);
                LimpiarTable();
                LimpiarDiscos();
                ListarDiscos();
            }
        }
    }//GEN-LAST:event_elimDiscBtnActionPerformed

    private void editDiscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDiscBtnActionPerformed
        if ("".equals(idDiscTxt.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila!");
        } else {
            if (!"".equals(codDiscTxt.getText()) || !"".equals(descDiscTxt.getText()) || !"".equals(cantDiscTxt.getText()) || !"".equals(precDiscTxt.getText())) {
                dis.setCodigo(codDiscTxt.getText());
                dis.setNombre(descDiscTxt.getText());
                dis.setProveedor(provDiscCbx.getSelectedItem().toString());
                dis.setStock(Integer.parseInt(cantDiscTxt.getText()));
                dis.setPrecio(Double.parseDouble(precDiscTxt.getText()));
                dis.setId(Integer.parseInt(idDiscTxt.getText()));
                disDao.ModificarDiscos(dis);
                JOptionPane.showMessageDialog(null, "Disco Modificado!");
                LimpiarTable();
                ListarDiscos();
                LimpiarDiscos();
            }
        }
    }//GEN-LAST:event_editDiscBtnActionPerformed

    private void excelDiscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelDiscBtnActionPerformed
        Excel.reportes();
    }//GEN-LAST:event_excelDiscBtnActionPerformed

    private void codVentaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codVentaTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(codVentaTxt.getText())) {
                String cod = codVentaTxt.getText();
                dis = disDao.BuscarDis(cod);
                if (dis.getNombre() != null) {
                    descVentaTxt.setText("" + dis.getNombre());
                    precVentaTxt.setText("" + dis.getPrecio());
                    stockDispTxt.setText("" + dis.getStock());
                    cantVentaTxt.requestFocus();
                } else {
                    LimpiarPedido();
                    codVentaTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el código del disco!");
            }
        }
    }//GEN-LAST:event_codVentaTxtKeyPressed

    private void cantVentaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantVentaTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(cantVentaTxt.getText())) {
                String cod = codVentaTxt.getText();
                String descripcion = descVentaTxt.getText();
                int cant = Integer.parseInt(cantVentaTxt.getText());
                double precio = Double.parseDouble(precVentaTxt.getText());
                double total = cant * precio;
                int stock = Integer.parseInt(stockDispTxt.getText());
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) tableVenta.getModel();
                    for (int i = 0; i < tableVenta.getRowCount(); i++) {
                        if (tableVenta.getValueAt(i, 1).equals(descVentaTxt.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya ha sido registrado!");
                            return;
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(cod);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);
                    Object[] o = new Object[5];
                    o[0] = lista.get(1);
                    o[1] = lista.get(2);
                    o[2] = lista.get(3);
                    o[3] = lista.get(4);
                    o[4] = lista.get(4);
                    tmp.addRow(o);
                    tableVenta.setModel(tmp);
                    TotalPagar();
                    LimpiarPedido();
                    codVentaTxt.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Stock no disponible!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese la cantidad!");
            }
        }
    }//GEN-LAST:event_cantVentaTxtKeyPressed

    private void ElimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElimBtnActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Quieres excluir la fila seleccionada?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            modelo = (DefaultTableModel) tableVenta.getModel();
            modelo.removeRow(tableVenta.getSelectedRow());
            TotalPagar();
            codVentaTxt.requestFocus();
        }
    }//GEN-LAST:event_ElimBtnActionPerformed

    private void dniVentaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dniVentaTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(dniVentaTxt.getText())) {
                int dni = Integer.parseInt(dniVentaTxt.getText());
                cl = client.BuscarCliente(dni);
                if (cl.getNombre() != null) {
                    nomCliVentaTxt.setText("" + cl.getNombre());
                    telCVTxt.setText("" + cl.getTelefono());
                    direcCVTxt.setText("" + cl.getDireccion());
                } else {
                    dniVentaTxt.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe!");
                }
            }
        }
    }//GEN-LAST:event_dniVentaTxtKeyPressed

    private void NuevaVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevaVentaBtnActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        LimpiarTable();
    }//GEN-LAST:event_NuevaVentaBtnActionPerformed

    private void codVentaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codVentaTxtKeyTyped
        even.numberKeyPress(evt);
    }//GEN-LAST:event_codVentaTxtKeyTyped

    private void descVentaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descVentaTxtKeyTyped
        even.textKeyPress(evt);
    }//GEN-LAST:event_descVentaTxtKeyTyped

    private void cantVentaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantVentaTxtKeyTyped
        even.numberKeyPress(evt);
    }//GEN-LAST:event_cantVentaTxtKeyTyped

    private void precVentaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precVentaTxtKeyTyped
        even.numberDecimalKeyPress(evt, precVentaTxt);
    }//GEN-LAST:event_precVentaTxtKeyTyped

    private void precDiscTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precDiscTxtKeyTyped
        even.numberDecimalKeyPress(evt, precDiscTxt);
    }//GEN-LAST:event_precDiscTxtKeyTyped

    private void actDatosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actDatosBtnActionPerformed
        if (!"".equals(rucDatosTxt.getText()) || !"".equals(nomDatosTxt.getText()) || !"".equals(telDatosTxt.getText()) || !"".equals(dirDatosTxt.getText())) {
            dat.setRuc(rucDatosTxt.getText());
            dat.setNombre(nomDatosTxt.getText());
            dat.setTelefono(telDatosTxt.getText());
            dat.setDireccion(dirDatosTxt.getText());
            dat.setId(Integer.parseInt(idDatosTxt.getText()));
            disDao.ModificarDatos(dat);
            JOptionPane.showMessageDialog(null, "Datos Actualizados!");
            ListarDatos();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos están vacíos!");
        }
    }//GEN-LAST:event_actDatosBtnActionPerformed

    private void ConfigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigBtnActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        ListarDatos();
    }//GEN-LAST:event_ConfigBtnActionPerformed

    private void VentasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentasBtnActionPerformed
        jTabbedPane1.setSelectedIndex(4);
        LimpiarTable();
        ListarPedidos();
    }//GEN-LAST:event_VentasBtnActionPerformed

    private void pdfVentasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfVentasBtnActionPerformed
        try {
            int id = Integer.parseInt(idVentaTxt.getText());
            File file = new File("src/pdf/pedido" + id + ".pdf");
            Desktop.getDesktop().open(file);

        } catch (IOException ex) {
            Logger.getLogger(Tienda.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pdfVentasBtnActionPerformed

    private void tablePdfVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePdfVentasMouseClicked
        int fila = tablePdfVentas.rowAtPoint(evt.getPoint());
        idVentaTxt.setText(tablePdfVentas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tablePdfVentasMouseClicked

    private void nuevoDiscBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoDiscBtnActionPerformed
        LimpiarDiscos();
    }//GEN-LAST:event_nuevoDiscBtnActionPerformed

    private void rucDatosTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rucDatosTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rucDatosTxtActionPerformed

    private void idVentaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idVentaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idVentaTxtActionPerformed

    private void tableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableVentaMouseClicked

    private void jTabbedPane1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbedPane1AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1AncestorMoved

    private void codDiscTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codDiscTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codDiscTxtActionPerformed

    private void provDiscCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provDiscCbxActionPerformed

    }//GEN-LAST:event_provDiscCbxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Tienda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tienda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tienda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tienda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tienda().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClientesBtn;
    private javax.swing.JButton ConfigBtn;
    private javax.swing.JButton DiscosBtn;
    private javax.swing.JButton ElimBtn;
    private javax.swing.JButton ElimCBtn;
    private javax.swing.JButton NuevaVentaBtn;
    private javax.swing.JButton ProveedorBtn;
    private javax.swing.JButton VentasBtn;
    private javax.swing.JButton actDatosBtn;
    private javax.swing.JTextField cantDiscTxt;
    private javax.swing.JTextField cantVentaTxt;
    private javax.swing.JTextField codDiscTxt;
    private javax.swing.JTextField codVentaTxt;
    private javax.swing.JTextField descDiscTxt;
    private javax.swing.JTextField descVentaTxt;
    private javax.swing.JTextField dirCliTxt;
    private javax.swing.JTextField dirDatosTxt;
    private javax.swing.JTextField dirProvTxt;
    private javax.swing.JTextField direcCVTxt;
    private javax.swing.JTextField dniCliTxt;
    private javax.swing.JTextField dniVentaTxt;
    private javax.swing.JButton editDiscBtn;
    private javax.swing.JButton editProvBtn;
    private javax.swing.JButton editarCBtn;
    private javax.swing.JButton elimDiscBtn;
    private javax.swing.JButton elimProvBtn;
    private javax.swing.JButton excelDiscBtn;
    private javax.swing.JButton facturaBtn;
    private javax.swing.JButton guardarCTxt;
    private javax.swing.JButton guardarDiscBtn;
    private javax.swing.JButton guardarProvBtn;
    private javax.swing.JTextField idClienteTxt;
    private javax.swing.JTextField idDatosTxt;
    private javax.swing.JTextField idDiscTxt;
    private javax.swing.JTextField idProvTxt;
    private javax.swing.JTextField idVentaTxt;
    private javax.swing.JTextField iddiscTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nomCliTxt;
    private javax.swing.JTextField nomCliVentaTxt;
    private javax.swing.JTextField nomDatosTxt;
    private javax.swing.JTextField nomProvTxt;
    private javax.swing.JButton nuevoCBtn;
    private javax.swing.JButton nuevoDiscBtn;
    private javax.swing.JButton nuevoProvBtn;
    private javax.swing.JButton pdfVentasBtn;
    private javax.swing.JTextField precDiscTxt;
    private javax.swing.JTextField precVentaTxt;
    private javax.swing.JComboBox<String> provDiscCbx;
    private javax.swing.JTextField rucDatosTxt;
    private javax.swing.JTextField rucProvTxt;
    private javax.swing.JTextField stockDispTxt;
    private javax.swing.JTable tableCliente;
    private javax.swing.JTable tableDisco;
    private javax.swing.JTable tablePdfVentas;
    private javax.swing.JTable tableProveedor;
    private javax.swing.JTable tableVenta;
    private javax.swing.JTextField telCVTxt;
    private javax.swing.JTextField telCliTxt;
    private javax.swing.JTextField telDatosTxt;
    private javax.swing.JTextField telProvTxt;
    private javax.swing.JTextField totalLabel;
    private javax.swing.JLabel vendedorLabel;
    // End of variables declaration//GEN-END:variables
    private void LimpiarCliente() {
        idClienteTxt.setText("");
        dniCliTxt.setText("");
        nomCliTxt.setText("");
        telCliTxt.setText("");
        dirCliTxt.setText("");

    }

    private void LimpiarProveedor() {
        idProvTxt.setText("");
        rucProvTxt.setText("");
        nomProvTxt.setText("");
        telProvTxt.setText("");
        dirProvTxt.setText("");

    }

    private void LimpiarDiscos() {
        idDiscTxt.setText("");
        codDiscTxt.setText("");
        provDiscCbx.setSelectedItem(null);
        descDiscTxt.setText("");
        cantDiscTxt.setText("");
        precDiscTxt.setText("");
    }

    private void TotalPagar() {
        Totalpagar = 0.00;
        int numFila = tableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double calc = Double.parseDouble(String.valueOf(tableVenta.getModel().getValueAt(i, 4)));
            Totalpagar = Totalpagar + calc;
        }
        totalLabel.setText(String.format("%.2f", Totalpagar));
    }

    private void LimpiarPedido() {
        codVentaTxt.setText("");
        descVentaTxt.setText("");
        cantVentaTxt.setText("");
        stockDispTxt.setText("");
        precVentaTxt.setText("");
        idVentaTxt.setText("");
    }

    private void RegistrarPedido() {
        String cliente = nomCliVentaTxt.getText();
        String vendedor = vendedorLabel.getText();
        double monto = Totalpagar;
        p.setCliente(cliente);
        p.setVendedor(vendedor);
        p.setTotal(monto);
        pDao.RegistrarPedidos(p);
    }

    private void RegistrarDetalle() {
        int id = pDao.IdPedido();
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(tableVenta.getValueAt(i, 3).toString());
            Dv.setCod_disco(cod);
            Dv.setCantidad(cant);
            Dv.setPrecio(precio);
            Dv.setId(id);
            pDao.RegistrarDetalle(Dv);
        }
    }

    private void ActualizarStock() {
        for (int i = 0; i < tableVenta.getRowCount(); i++) {
            String cod = tableVenta.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(tableVenta.getValueAt(i, 2).toString());
            dis = disDao.BuscarDis(cod);
            int StockActual = dis.getStock() - cant;
            pDao.ActualizarStock(StockActual, cod);
        }
    }

    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) tableVenta.getModel();
        int fila = tableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);

        }
    }

    private void LimpiarClienteVenta() {
        dniVentaTxt.setText("");
        nomCliVentaTxt.setText("");
        telCVTxt.setText("");
        direcCVTxt.setText("");
    }

    private void pdf() {
        try {
            int id = pDao.IdPedido();
            FileOutputStream archivo;
            File file = new File("src/pdf/pedido" + id + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src/misimg/tocadiscos.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura:" + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);

            String ruc = rucDatosTxt.getText();
            String nom = nomDatosTxt.getText();
            String tel = telDatosTxt.getText();
            String dir = dirDatosTxt.getText();

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nom + "\nTeléfono: " + tel + "\nDirección: " + dir);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de los Clientes:" + "\n\n");
            doc.add(cli);

            PdfPTable tablacli = new PdfPTable(4);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{20f, 50f, 30f, 40f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell cl1 = new PdfPCell(new Phrase("Dni/Ruc", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("Teléfono", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("Dirección", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
            tablacli.addCell(dniVentaTxt.getText());
            tablacli.addCell(nomCliVentaTxt.getText());
            tablacli.addCell(telCVTxt.getText());
            tablacli.addCell(direcCVTxt.getText());

            doc.add(tablacli);

            //productos
            PdfPTable tabladis = new PdfPTable(4);
            tabladis.setWidthPercentage(100);
            tabladis.getDefaultCell().setBorder(0);
            float[] Columnadis = new float[]{10f, 50f, 15f, 20f};
            tabladis.setWidths(Columnadis);
            tabladis.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell dis1 = new PdfPCell(new Phrase("Cant.", negrita));
            PdfPCell dis2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell dis3 = new PdfPCell(new Phrase("Precio U.", negrita));
            PdfPCell dis4 = new PdfPCell(new Phrase("Precio T.", negrita));
            dis1.setBorder(0);
            dis2.setBorder(0);
            dis3.setBorder(0);
            dis4.setBorder(0);

            dis1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            dis2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            dis3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            dis4.setBackgroundColor(BaseColor.LIGHT_GRAY);

            tabladis.addCell(dis1);
            tabladis.addCell(dis2);
            tabladis.addCell(dis3);
            tabladis.addCell(dis4);
            for (int i = 0; i < tableVenta.getRowCount(); i++) {
                String disco = tableVenta.getValueAt(i, 1).toString();
                String cantidad = tableVenta.getValueAt(i, 2).toString();
                String precio = tableVenta.getValueAt(i, 3).toString();
                String total = tableVenta.getValueAt(i, 4).toString();
                tabladis.addCell(cantidad);
                tabladis.addCell(disco);
                tabladis.addCell(precio);
                tabladis.addCell(total);
            }
            doc.add(tabladis);

            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("Total a Pagar: " + Totalpagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelación y Firma\n\n");
            firma.add("-----------------------");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
}
