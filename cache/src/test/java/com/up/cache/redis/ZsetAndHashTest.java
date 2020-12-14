package com.up.cache.redis;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ZsetAndHashTest {


    //    private StringRedisTemplate redisTemplate;
//
//    private ValueOperations<String, String> valueOps;
//
//    private HashOperations<String, String, String> hashOps;
//
//    private ListOperations<String, String> listOps;
//
//    private SetOperations<String, String> setOps;
//    @Autowired
//    private ZSetOperations<String, String> zsetOps;
//
//    public long zadd(String key, Map<String, Double> map) {
//        try {
//            if (map == null || map.size() == 0) {
//                return 0;
//            }
//            Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>(map.size());
//            for (String k : map.keySet()) {
//                DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>(k, map.get(k));
//                tuples.add(tuple);
//            }
//            return zsetOps.add(genKey(key), tuples);
//        } catch (Throwable e) {
//            throw e;
//        }
//    }
//
//    private String genKey(String key) {
//        return key;
//    }
//
//
//    @Test
//    public void randTest() {
//        Random random = new Random();
//
//
//        for (int j = 0; j < 100; j++) {
//            int i = random.nextInt(30);
//            System.out.println(i);
//        }
//
//    }
//
//    @Test
//    public void zsetTest() {
//        Map<String, BigDecimal> map = new HashMap<>(9);
//        map.put("100110", BigDecimal.valueOf(200.00D));
//        map.put("100111", BigDecimal.valueOf(201.00D));
//        map.put("100112", BigDecimal.valueOf(202.00D));
//        map.put("100113", BigDecimal.valueOf(203.00D));
//        map.put("100114", BigDecimal.valueOf(204.00D));
//        map.put("100115", BigDecimal.valueOf(205.00D));
//        Map<String, Double> newMap = map.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().doubleValue()));
//
//        zadd(String.valueOf(1L), newMap);
//        System.out.println(JSON.toJSONString(map, true));
//    }
    @Test
    public void stringTest() {
        System.out.println("".equals(null
        ));

    }
}
