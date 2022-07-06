import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class App {
    /*
     * Problem statement:
     * Work out the first ten digits of the sum of the following one-hundred,
     * 50-digit numbers.
     * 37107287533902102798797998220837590246510135740250
     * 46376937677490009712648124896970078050417018260538
     * 74324986199524741059474233309513058123726617309629
     * 91942213363574161572522430563301811072406154908250
     * 23067588207539346171171980310421047513778063246676
     * 89261670696623633820136378418383684178734361726757
     * 28112879812849979408065481931592621691275889832738
     * 44274228917432520321923589422876796487670272189318
     * 47451445736001306439091167216856844588711603153276
     * 70386486105843025439939619828917593665686757934951
     * 62176457141856560629502157223196586755079324193331
     * 64906352462741904929101432445813822663347944758178
     * 92575867718337217661963751590579239728245598838407
     * 58203565325359399008402633568948830189458628227828
     * 80181199384826282014278194139940567587151170094390
     * 35398664372827112653829987240784473053190104293586
     * 86515506006295864861532075273371959191420517255829
     * 71693888707715466499115593487603532921714970056938
     * 54370070576826684624621495650076471787294438377604
     * 53282654108756828443191190634694037855217779295145
     * 36123272525000296071075082563815656710885258350721
     * 45876576172410976447339110607218265236877223636045
     * 17423706905851860660448207621209813287860733969412
     * 81142660418086830619328460811191061556940512689692
     * 51934325451728388641918047049293215058642563049483
     * 62467221648435076201727918039944693004732956340691
     * 15732444386908125794514089057706229429197107928209
     * 55037687525678773091862540744969844508330393682126
     * 18336384825330154686196124348767681297534375946515
     * 80386287592878490201521685554828717201219257766954
     * 78182833757993103614740356856449095527097864797581
     * 16726320100436897842553539920931837441497806860984
     * 48403098129077791799088218795327364475675590848030
     * 87086987551392711854517078544161852424320693150332
     * 59959406895756536782107074926966537676326235447210
     * 69793950679652694742597709739166693763042633987085
     * 41052684708299085211399427365734116182760315001271
     * 65378607361501080857009149939512557028198746004375
     * 35829035317434717326932123578154982629742552737307
     * 94953759765105305946966067683156574377167401875275
     * 88902802571733229619176668713819931811048770190271
     * 25267680276078003013678680992525463401061632866526
     * 36270218540497705585629946580636237993140746255962
     * 24074486908231174977792365466257246923322810917141
     * 91430288197103288597806669760892938638285025333403
     * 34413065578016127815921815005561868836468420090470
     * 23053081172816430487623791969842487255036638784583
     * 11487696932154902810424020138335124462181441773470
     * 63783299490636259666498587618221225225512486764533
     * 67720186971698544312419572409913959008952310058822
     * 95548255300263520781532296796249481641953868218774
     * 76085327132285723110424803456124867697064507995236
     * 37774242535411291684276865538926205024910326572967
     * 23701913275725675285653248258265463092207058596522
     * 29798860272258331913126375147341994889534765745501
     * 18495701454879288984856827726077713721403798879715
     * 38298203783031473527721580348144513491373226651381
     * 34829543829199918180278916522431027392251122869539
     * 40957953066405232632538044100059654939159879593635
     * 29746152185502371307642255121183693803580388584903
     * 41698116222072977186158236678424689157993532961922
     * 62467957194401269043877107275048102390895523597457
     * 23189706772547915061505504953922979530901129967519
     * 86188088225875314529584099251203829009407770775672
     * 11306739708304724483816533873502340845647058077308
     * 82959174767140363198008187129011875491310547126581
     * 97623331044818386269515456334926366572897563400500
     * 42846280183517070527831839425882145521227251250327
     * 55121603546981200581762165212827652751691296897789
     * 32238195734329339946437501907836945765883352399886
     * 75506164965184775180738168837861091527357929701337
     * 62177842752192623401942399639168044983993173312731
     * 32924185707147349566916674687634660915035914677504
     * 99518671430235219628894890102423325116913619626622
     * 73267460800591547471830798392868535206946944540724
     * 76841822524674417161514036427982273348055556214818
     * 97142617910342598647204516893989422179826088076852
     * 87783646182799346313767754307809363333018982642090
     * 10848802521674670883215120185883543223812876952786
     * 71329612474782464538636993009049310363619763878039
     * 62184073572399794223406235393808339651327408011116
     * 66627891981488087797941876876144230030984490851411
     * 60661826293682836764744779239180335110989069790714
     * 85786944089552990653640447425576083659976645795096
     * 66024396409905389607120198219976047599490197230297
     * 64913982680032973156037120041377903785566085089252
     * 16730939319872750275468906903707539413042652315011
     * 94809377245048795150954100921645863754710598436791
     * 78639167021187492431995700641917969777599028300699
     * 15368713711936614952811305876380278410754449733078
     * 40789923115535562561142322423255033685442488917353
     * 44889911501440648020369068063960672322193204149535
     * 41503128880339536053299340368006977710650566631954
     * 81234880673210146739058568557934581403627822703280
     * 82616570773948327592232845941706525094512325230608
     * 22918802058777319719839450180888072429661980811197
     * 77158542502016545090413245809786882778948721859617
     * 72107838435069186155435662884062257473692284509516
     * 20849603980134001723930671666823555245252804609722
     * 53503534226472524250874054075591789781264330331690
     */
    public static void main(String[] args) {
        List<Long> longs = new ArrayList<Long>();
        List<BigInteger> bigInts = new ArrayList<BigInteger>();

        BigInteger num1 = new BigInteger("37107287533902102798797998220837590246510135740250");
        BigInteger num2 = new BigInteger("46376937677490009712648124896970078050417018260538");
        BigInteger num3 = new BigInteger("74324986199524741059474233309513058123726617309629");
        BigInteger num4 = new BigInteger("91942213363574161572522430563301811072406154908250");
        BigInteger num5 = new BigInteger("23067588207539346171171980310421047513778063246676");
        BigInteger num6 = new BigInteger("89261670696623633820136378418383684178734361726757");
        BigInteger num7 = new BigInteger("28112879812849979408065481931592621691275889832738");
        BigInteger num8 = new BigInteger("44274228917432520321923589422876796487670272189318");
        BigInteger num9 = new BigInteger("47451445736001306439091167216856844588711603153276");
        BigInteger num10 = new BigInteger("70386486105843025439939619828917593665686757934951");
        BigInteger num11 = new BigInteger("62176457141856560629502157223196586755079324193331");
        BigInteger num12 = new BigInteger("64906352462741904929101432445813822663347944758178");
        BigInteger num13 = new BigInteger("92575867718337217661963751590579239728245598838407");
        BigInteger num14 = new BigInteger("58203565325359399008402633568948830189458628227828");
        BigInteger num15 = new BigInteger("80181199384826282014278194139940567587151170094390");
        BigInteger num16 = new BigInteger("35398664372827112653829987240784473053190104293586");
        BigInteger num17 = new BigInteger("86515506006295864861532075273371959191420517255829");
        BigInteger num18 = new BigInteger("71693888707715466499115593487603532921714970056938");
        BigInteger num19 = new BigInteger("54370070576826684624621495650076471787294438377604");
        BigInteger num20 = new BigInteger("53282654108756828443191190634694037855217779295145");
        BigInteger num21 = new BigInteger("36123272525000296071075082563815656710885258350721");
        BigInteger num22 = new BigInteger("45876576172410976447339110607218265236877223636045");
        BigInteger num23 = new BigInteger("17423706905851860660448207621209813287860733969412");
        BigInteger num24 = new BigInteger("81142660418086830619328460811191061556940512689692");
        BigInteger num25 = new BigInteger("51934325451728388641918047049293215058642563049483");
        BigInteger num26 = new BigInteger("62467221648435076201727918039944693004732956340691");
        BigInteger num27 = new BigInteger("15732444386908125794514089057706229429197107928209");
        BigInteger num28 = new BigInteger("55037687525678773091862540744969844508330393682126");
        BigInteger num29 = new BigInteger("18336384825330154686196124348767681297534375946515");
        BigInteger num30 = new BigInteger("80386287592878490201521685554828717201219257766954");
        BigInteger num31 = new BigInteger("78182833757993103614740356856449095527097864797581");
        BigInteger num32 = new BigInteger("16726320100436897842553539920931837441497806860984");
        BigInteger num33 = new BigInteger("48403098129077791799088218795327364475675590848030");
        BigInteger num34 = new BigInteger("87086987551392711854517078544161852424320693150332");
        BigInteger num35 = new BigInteger("59959406895756536782107074926966537676326235447210");
        BigInteger num36 = new BigInteger("69793950679652694742597709739166693763042633987085");
        BigInteger num37 = new BigInteger("41052684708299085211399427365734116182760315001271");
        BigInteger num38 = new BigInteger("65378607361501080857009149939512557028198746004375");
        BigInteger num39 = new BigInteger("35829035317434717326932123578154982629742552737307");
        BigInteger num40 = new BigInteger("94953759765105305946966067683156574377167401875275");
        BigInteger num41 = new BigInteger("88902802571733229619176668713819931811048770190271");
        BigInteger num42 = new BigInteger("25267680276078003013678680992525463401061632866526");
        BigInteger num43 = new BigInteger("36270218540497705585629946580636237993140746255962");
        BigInteger num44 = new BigInteger("24074486908231174977792365466257246923322810917141");
        BigInteger num45 = new BigInteger("91430288197103288597806669760892938638285025333403");
        BigInteger num46 = new BigInteger("34413065578016127815921815005561868836468420090470");
        BigInteger num47 = new BigInteger("23053081172816430487623791969842487255036638784583");
        BigInteger num48 = new BigInteger("11487696932154902810424020138335124462181441773470");
        BigInteger num49 = new BigInteger("63783299490636259666498587618221225225512486764533");
        BigInteger num50 = new BigInteger("67720186971698544312419572409913959008952310058822");
        BigInteger num51 = new BigInteger("95548255300263520781532296796249481641953868218774");
        BigInteger num52 = new BigInteger("76085327132285723110424803456124867697064507995236");
        BigInteger num53 = new BigInteger("37774242535411291684276865538926205024910326572967");
        BigInteger num54 = new BigInteger("23701913275725675285653248258265463092207058596522");
        BigInteger num55 = new BigInteger("29798860272258331913126375147341994889534765745501");
        BigInteger num56 = new BigInteger("18495701454879288984856827726077713721403798879715");
        BigInteger num57 = new BigInteger("38298203783031473527721580348144513491373226651381");
        BigInteger num58 = new BigInteger("34829543829199918180278916522431027392251122869539");
        BigInteger num59 = new BigInteger("40957953066405232632538044100059654939159879593635");
        BigInteger num60 = new BigInteger("29746152185502371307642255121183693803580388584903");
        BigInteger num61 = new BigInteger("41698116222072977186158236678424689157993532961922");
        BigInteger num62 = new BigInteger("62467957194401269043877107275048102390895523597457");
        BigInteger num63 = new BigInteger("23189706772547915061505504953922979530901129967519");
        BigInteger num64 = new BigInteger("86188088225875314529584099251203829009407770775672");
        BigInteger num65 = new BigInteger("11306739708304724483816533873502340845647058077308");
        BigInteger num66 = new BigInteger("82959174767140363198008187129011875491310547126581");
        BigInteger num67 = new BigInteger("97623331044818386269515456334926366572897563400500");
        BigInteger num68 = new BigInteger("42846280183517070527831839425882145521227251250327");
        BigInteger num69 = new BigInteger("55121603546981200581762165212827652751691296897789");
        BigInteger num70 = new BigInteger("32238195734329339946437501907836945765883352399886");
        BigInteger num71 = new BigInteger("75506164965184775180738168837861091527357929701337");
        BigInteger num72 = new BigInteger("62177842752192623401942399639168044983993173312731");
        BigInteger num73 = new BigInteger("32924185707147349566916674687634660915035914677504");
        BigInteger num74 = new BigInteger("99518671430235219628894890102423325116913619626622");
        BigInteger num75 = new BigInteger("73267460800591547471830798392868535206946944540724");
        BigInteger num76 = new BigInteger("76841822524674417161514036427982273348055556214818");
        BigInteger num77 = new BigInteger("97142617910342598647204516893989422179826088076852");
        BigInteger num78 = new BigInteger("87783646182799346313767754307809363333018982642090");
        BigInteger num79 = new BigInteger("10848802521674670883215120185883543223812876952786");
        BigInteger num80 = new BigInteger("71329612474782464538636993009049310363619763878039");
        BigInteger num81 = new BigInteger("62184073572399794223406235393808339651327408011116");
        BigInteger num82 = new BigInteger("66627891981488087797941876876144230030984490851411");
        BigInteger num83 = new BigInteger("60661826293682836764744779239180335110989069790714");
        BigInteger num84 = new BigInteger("85786944089552990653640447425576083659976645795096");
        BigInteger num85 = new BigInteger("66024396409905389607120198219976047599490197230297");
        BigInteger num86 = new BigInteger("64913982680032973156037120041377903785566085089252");
        BigInteger num87 = new BigInteger("16730939319872750275468906903707539413042652315011");
        BigInteger num88 = new BigInteger("94809377245048795150954100921645863754710598436791");
        BigInteger num89 = new BigInteger("78639167021187492431995700641917969777599028300699");
        BigInteger num90 = new BigInteger("15368713711936614952811305876380278410754449733078");
        BigInteger num91 = new BigInteger("40789923115535562561142322423255033685442488917353");
        BigInteger num92 = new BigInteger("44889911501440648020369068063960672322193204149535");
        BigInteger num93 = new BigInteger("41503128880339536053299340368006977710650566631954");
        BigInteger num94 = new BigInteger("81234880673210146739058568557934581403627822703280");
        BigInteger num95 = new BigInteger("82616570773948327592232845941706525094512325230608");
        BigInteger num96 = new BigInteger("22918802058777319719839450180888072429661980811197");
        BigInteger num97 = new BigInteger("77158542502016545090413245809786882778948721859617");
        BigInteger num98 = new BigInteger("72107838435069186155435662884062257473692284509516");
        BigInteger num99 = new BigInteger("20849603980134001723930671666823555245252804609722");
        BigInteger num100 = new BigInteger("53503534226472524250874054075591789781264330331690");

        bigInts.add(num1);
        bigInts.add(num2);
        bigInts.add(num3);
        bigInts.add(num4);
        bigInts.add(num5);
        bigInts.add(num6);
        bigInts.add(num7);
        bigInts.add(num8);
        bigInts.add(num9);
        bigInts.add(num10);
        bigInts.add(num11);
        bigInts.add(num12);
        bigInts.add(num13);
        bigInts.add(num14);
        bigInts.add(num15);
        bigInts.add(num16);
        bigInts.add(num17);
        bigInts.add(num18);
        bigInts.add(num19);
        bigInts.add(num20);
        bigInts.add(num21);
        bigInts.add(num22);
        bigInts.add(num23);
        bigInts.add(num24);
        bigInts.add(num25);
        bigInts.add(num26);
        bigInts.add(num27);
        bigInts.add(num28);
        bigInts.add(num29);
        bigInts.add(num30);
        bigInts.add(num31);
        bigInts.add(num32);
        bigInts.add(num33);
        bigInts.add(num34);
        bigInts.add(num35);
        bigInts.add(num36);
        bigInts.add(num37);
        bigInts.add(num38);
        bigInts.add(num39);
        bigInts.add(num40);
        bigInts.add(num41);
        bigInts.add(num42);
        bigInts.add(num43);
        bigInts.add(num44);
        bigInts.add(num45);
        bigInts.add(num46);
        bigInts.add(num47);
        bigInts.add(num48);
        bigInts.add(num49);
        bigInts.add(num50);
        bigInts.add(num51);
        bigInts.add(num52);
        bigInts.add(num53);
        bigInts.add(num54);
        bigInts.add(num55);
        bigInts.add(num56);
        bigInts.add(num57);
        bigInts.add(num58);
        bigInts.add(num59);
        bigInts.add(num60);
        bigInts.add(num61);
        bigInts.add(num62);
        bigInts.add(num63);
        bigInts.add(num64);
        bigInts.add(num65);
        bigInts.add(num66);
        bigInts.add(num67);
        bigInts.add(num68);
        bigInts.add(num69);
        bigInts.add(num70);
        bigInts.add(num71);
        bigInts.add(num72);
        bigInts.add(num73);
        bigInts.add(num74);
        bigInts.add(num75);
        bigInts.add(num76);
        bigInts.add(num77);
        bigInts.add(num78);
        bigInts.add(num79);
        bigInts.add(num80);
        bigInts.add(num81);
        bigInts.add(num82);
        bigInts.add(num83);
        bigInts.add(num84);
        bigInts.add(num85);
        bigInts.add(num86);
        bigInts.add(num87);
        bigInts.add(num88);
        bigInts.add(num89);
        bigInts.add(num90);
        bigInts.add(num91);
        bigInts.add(num92);
        bigInts.add(num93);
        bigInts.add(num94);
        bigInts.add(num95);
        bigInts.add(num96);
        bigInts.add(num97);
        bigInts.add(num98);
        bigInts.add(num99);
        bigInts.add(num100);

        Long lNum1 = num1.longValue();
        Long lNum2 = num2.longValue();
        Long lNum3 = num3.longValue();
        Long lNum4 = num4.longValue();
        Long lNum5 = num5.longValue();
        Long lNum6 = num6.longValue();
        Long lNum7 = num7.longValue();
        Long lNum8 = num8.longValue();
        Long lNum9 = num9.longValue();
        Long lNum10 = num10.longValue();
        Long lNum11 = num11.longValue();
        Long lNum12 = num12.longValue();
        Long lNum13 = num13.longValue();
        Long lNum14 = num14.longValue();
        Long lNum15 = num15.longValue();
        Long lNum16 = num16.longValue();
        Long lNum17 = num17.longValue();
        Long lNum18 = num18.longValue();
        Long lNum19 = num19.longValue();
        Long lNum20 = num20.longValue();
        Long lNum21 = num21.longValue();
        Long lNum22 = num22.longValue();
        Long lNum23 = num23.longValue();
        Long lNum24 = num24.longValue();
        Long lNum25 = num25.longValue();
        Long lNum26 = num26.longValue();
        Long lNum27 = num27.longValue();
        Long lNum28 = num28.longValue();
        Long lNum29 = num29.longValue();
        Long lNum30 = num30.longValue();
        Long lNum31 = num31.longValue();
        Long lNum32 = num32.longValue();
        Long lNum33 = num33.longValue();
        Long lNum34 = num34.longValue();
        Long lNum35 = num35.longValue();
        Long lNum36 = num36.longValue();
        Long lNum37 = num37.longValue();
        Long lNum38 = num38.longValue();
        Long lNum39 = num39.longValue();
        Long lNum40 = num40.longValue();
        Long lNum41 = num41.longValue();
        Long lNum42 = num42.longValue();
        Long lNum43 = num43.longValue();
        Long lNum44 = num44.longValue();
        Long lNum45 = num45.longValue();
        Long lNum46 = num46.longValue();
        Long lNum47 = num47.longValue();
        Long lNum48 = num48.longValue();
        Long lNum49 = num49.longValue();
        Long lNum50 = num50.longValue();
        Long lNum51 = num51.longValue();
        Long lNum52 = num52.longValue();
        Long lNum53 = num53.longValue();
        Long lNum54 = num54.longValue();
        Long lNum55 = num55.longValue();
        Long lNum56 = num56.longValue();
        Long lNum57 = num57.longValue();
        Long lNum58 = num58.longValue();
        Long lNum59 = num59.longValue();
        Long lNum60 = num60.longValue();
        Long lNum61 = num61.longValue();
        Long lNum62 = num62.longValue();
        Long lNum63 = num63.longValue();
        Long lNum64 = num64.longValue();
        Long lNum65 = num65.longValue();
        Long lNum66 = num66.longValue();
        Long lNum67 = num67.longValue();
        Long lNum68 = num68.longValue();
        Long lNum69 = num69.longValue();
        Long lNum70 = num70.longValue();
        Long lNum71 = num71.longValue();
        Long lNum72 = num72.longValue();
        Long lNum73 = num73.longValue();
        Long lNum74 = num74.longValue();
        Long lNum75 = num75.longValue();
        Long lNum76 = num76.longValue();
        Long lNum77 = num77.longValue();
        Long lNum78 = num78.longValue();
        Long lNum79 = num79.longValue();
        Long lNum80 = num80.longValue();
        Long lNum81 = num81.longValue();
        Long lNum82 = num82.longValue();
        Long lNum83 = num83.longValue();
        Long lNum84 = num84.longValue();
        Long lNum85 = num85.longValue();
        Long lNum86 = num86.longValue();
        Long lNum87 = num87.longValue();
        Long lNum88 = num88.longValue();
        Long lNum89 = num89.longValue();
        Long lNum90 = num90.longValue();
        Long lNum91 = num91.longValue();
        Long lNum92 = num92.longValue();
        Long lNum93 = num93.longValue();
        Long lNum94 = num94.longValue();
        Long lNum95 = num95.longValue();
        Long lNum96 = num96.longValue();
        Long lNum97 = num97.longValue();
        Long lNum98 = num98.longValue();
        Long lNum99 = num99.longValue();
        Long lNum100 = num100.longValue();

        longs.add(lNum1);
        longs.add(lNum2);
        longs.add(lNum3);
        longs.add(lNum4);
        longs.add(lNum5);
        longs.add(lNum6);
        longs.add(lNum7);
        longs.add(lNum8);
        longs.add(lNum9);
        longs.add(lNum10);
        longs.add(lNum11);
        longs.add(lNum12);
        longs.add(lNum13);
        longs.add(lNum14);
        longs.add(lNum15);
        longs.add(lNum16);
        longs.add(lNum17);
        longs.add(lNum18);
        longs.add(lNum19);
        longs.add(lNum20);
        longs.add(lNum21);
        longs.add(lNum22);
        longs.add(lNum23);
        longs.add(lNum24);
        longs.add(lNum25);
        longs.add(lNum26);
        longs.add(lNum27);
        longs.add(lNum28);
        longs.add(lNum29);
        longs.add(lNum30);
        longs.add(lNum31);
        longs.add(lNum32);
        longs.add(lNum33);
        longs.add(lNum34);
        longs.add(lNum35);
        longs.add(lNum36);
        longs.add(lNum37);
        longs.add(lNum38);
        longs.add(lNum39);
        longs.add(lNum40);
        longs.add(lNum41);
        longs.add(lNum42);
        longs.add(lNum43);
        longs.add(lNum44);
        longs.add(lNum45);
        longs.add(lNum46);
        longs.add(lNum47);
        longs.add(lNum48);
        longs.add(lNum49);
        longs.add(lNum50);
        longs.add(lNum51);
        longs.add(lNum52);
        longs.add(lNum53);
        longs.add(lNum54);
        longs.add(lNum55);
        longs.add(lNum56);
        longs.add(lNum57);
        longs.add(lNum58);
        longs.add(lNum59);
        longs.add(lNum60);
        longs.add(lNum61);
        longs.add(lNum62);
        longs.add(lNum63);
        longs.add(lNum64);
        longs.add(lNum65);
        longs.add(lNum66);
        longs.add(lNum67);
        longs.add(lNum68);
        longs.add(lNum69);
        longs.add(lNum70);
        longs.add(lNum71);
        longs.add(lNum72);
        longs.add(lNum73);
        longs.add(lNum74);
        longs.add(lNum75);
        longs.add(lNum76);
        longs.add(lNum77);
        longs.add(lNum78);
        longs.add(lNum79);
        longs.add(lNum80);
        longs.add(lNum81);
        longs.add(lNum82);
        longs.add(lNum83);
        longs.add(lNum84);
        longs.add(lNum85);
        longs.add(lNum86);
        longs.add(lNum87);
        longs.add(lNum88);
        longs.add(lNum89);
        longs.add(lNum90);
        longs.add(lNum91);
        longs.add(lNum92);
        longs.add(lNum93);
        longs.add(lNum94);
        longs.add(lNum95);
        longs.add(lNum96);
        longs.add(lNum97);
        longs.add(lNum98);
        longs.add(lNum99);
        longs.add(lNum100);

        System.out.println("Sum of BigIntegers is: " + addBigInts(bigInts));
        System.out.println("Sum of longs is: " + addLongs(longs));
    }

    private static BigInteger addBigInts(List<BigInteger> inputs) {
        BigInteger sum = inputs.get(0);
        BigInteger val = inputs.get(1);
        sum = sum.add(val);
        for (int i = 2; i < inputs.size(); i++) {
            val = inputs.get(i);
            sum = sum.add(val);
        }
        return sum;
    }

    private static Long addLongs(List<Long> inputs) {
        Long sum = 0L;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i);
        }
        return sum;
    }
}
