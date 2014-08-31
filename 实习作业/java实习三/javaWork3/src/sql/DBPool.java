package sql;

public class DBPool {
	private static MyConnection pool = new MyConnection();
	
	public static MyConnection getPool(){
		System.out.println(pool.connPool.size());
		return pool;
	}
}
