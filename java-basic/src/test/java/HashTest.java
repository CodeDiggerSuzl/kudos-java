/**
 * <p></p>
 *
 * @author suzailong
 * @date 2020/12/29 10:22 下午
 */
public class HashTest {

    public static void main(String[] args) {
        String shardingValue = "611664614715";
        int dbTotalCount = 4;// 总库数
        int tableTotalCount = 256;// 总表数
        int hash = shardingValue.hashCode();
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        int tableNo = hash % tableTotalCount;
        int dbNo = tableNo / (tableTotalCount / dbTotalCount);
        String dbKey = String.format("%02d", dbNo);
        String tableKey = String.format("%04d", tableNo);
        System.out.println(dbKey);
        System.out.println(tableKey);

    }
}
