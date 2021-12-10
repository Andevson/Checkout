import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String string_connection = "";
    private static Connection con = null;
    private static Statement st = null;
    private static ResultSet res = null;
    private static void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        con = DriverManager.getConnection(string_connection);
        st = con.createStatement();
    }
    private static void encerrarConexao(){
        if(res != null){
            try {
                res.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    public static List<PedidoORM> obterPedidos(){
        List<PedidoORM> pedidos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery("SELECT ped.nu_ped AS 'Pedido', ped.cd_clien AS 'Cod. cliente', COUNT(prod.nu_ped) AS 'Quantidade' FROM MOINHO.dbo.ped_vda as ped INNER JOIN MOINHO.dbo.it_pedv AS prod ON ped.nu_ped = prod.nu_ped WHERE ped.situacao = 'AB' AND prod.situacao = 'AB' GROUP BY prod.nu_ped, ped.nu_ped, ped.cd_clien;");
            while(res.next()){
                pedidos.add(new PedidoORM(res.getString("Pedido"), res.getString("Quantidade"), res.getString("Cod. cliente")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return pedidos;
    }
    public static List<ProdutoORM> obterProdutos(){
        List<ProdutoORM> produtos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery("SELECT MOINHO.dbo.it_pedv.nu_ped AS 'Pedido', MOINHO.dbo.it_pedv.cd_prod AS 'Produto', MOINHO.dbo.it_pedv.qtde AS 'Quantidade' FROM MOINHO.dbo.it_pedv WHERE MOINHO.dbo.it_pedv.situacao = 'AB';");
            while(res.next()){
                produtos.add(new ProdutoORM(res.getString("Pedido"), res.getString("Produto"), res.getInt("Quantidade")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return produtos;
    }
    public static List<ProdutoORM> obterProdutos(String numero_pedido){
        List<ProdutoORM> produtos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery("SELECT MOINHO.dbo.it_pedv.nu_ped AS 'Pedido', MOINHO.dbo.it_pedv.cd_prod AS 'Produto', MOINHO.dbo.it_pedv.qtde AS 'Quantidade' FROM MOINHO.dbo.it_pedv WHERE MOINHO.dbo.it_pedv.situacao = 'AB' AND MOINHO.dbo.it_pedv.nu_ped = " + numero_pedido + ";");
            while(res.next()){
                produtos.add(new ProdutoORM(res.getString("Pedido"), res.getString("Produto"), res.getInt("Quantidade")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return produtos;
    }
    public static int obterFator(String produto){
        try{
            conectar();
            res = st.executeQuery("SELECT MOINHO.dbo.produto.qtde_multipla AS 'Fator' FROM MOINHO.dbo.produto WHERE MOINHO.dbo.produto.cd_prod = " + produto + ";");
            if(res.next()){
                return res.getInt("Fator");
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return 1;
    }
}
