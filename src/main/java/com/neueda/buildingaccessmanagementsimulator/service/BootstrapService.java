package com.neueda.buildingaccessmanagementsimulator.service;

import com.neueda.buildingaccessmanagementsimulator.data.AccessRepository;
import com.neueda.buildingaccessmanagementsimulator.data.BuildingRepository;
import com.neueda.buildingaccessmanagementsimulator.data.UserRepository;
import com.neueda.buildingaccessmanagementsimulator.model.AccessRecord;
import com.neueda.buildingaccessmanagementsimulator.model.Building;
import com.neueda.buildingaccessmanagementsimulator.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BootstrapService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    AccessRepository accessRepository;

    @PostConstruct
    public void loadData() {
        loadUsers();
        loadBuildings();
        if (accessRepository.count() == 0) {
            loadAccessRecords(1);
            loadAccessRecords(2);
            loadAccessRecords(3);
            loadAccessRecords(4);
            loadAccessRecords(5);
        }
    }

    public void loadUsers() {
        if (userRepository.count() == 0) {
            List<User> users = Arrays.stream(userData).map(line -> {
                String[] fields = line.split(",");
                return new User(Integer.parseInt(fields[0]), fields[1], fields[2]);
            }).collect(Collectors.toList());
            userRepository.saveAll(users);
        }
    }

    public void loadBuildings() {
        if (buildingRepository.count() == 0) {
            List<Building> buildings = Arrays.stream(buildingData).map(line -> {
                String[] fields = line.split(",");
                return new Building(Integer.parseInt(fields[0]), fields[1]);
            }).collect(Collectors.toList());
            buildingRepository.saveAll(buildings);
        }
    }

    public void loadAccessRecords(int id) {

        String[] accessData;
        switch(id) {
            case 1: DataStore1 dataStore1 = new DataStore1();
                    accessData = dataStore1.accessData1;
                break;
            case 2: DataStore2 dataStore2 = new DataStore2();
                accessData = dataStore2.accessData2;
                break;
            case 3: DataStore3 dataStore3 = new DataStore3();
                accessData = dataStore3.accessData3;
                break;
            case 4: DataStore4 dataStore4 = new DataStore4();
                accessData = dataStore4.accessData4;
                break;
            default: DataStore5 dataStore5 = new DataStore5();
                accessData = dataStore5.accessData5;
                break;
        }


            List<AccessRecord> accessRecords = Arrays.stream(accessData).map(line -> {
                String[] fields = line.split(",");
                User u = userRepository.findById(Integer.parseInt(fields[0])).get();
                Building b = buildingRepository.findById(Integer.parseInt(fields[3])).get();
                return new AccessRecord(0,id,
                        u,
                        LocalTime.of(Integer.parseInt(fields[1]), Integer.parseInt(fields[2])),
                        b,
                        fields[4].equals("in")
                );
            }).collect(Collectors.toList());
            accessRepository.saveAll(accessRecords);

    }

    private String[] userData = {
            "1,Martin,Martin",
            "2,Ada,Davis",
            "3,John,Nelson",
            "4,Stella,Mason",
            "5,Briony,Moore",
            "6,Amanda,Carroll",
            "7,Heather,Payne",
            "8,Ryan,Davis",
            "9,Paige,Harper",
            "10,Nicholas,Riley",
            "11,Honey,Johnson",
            "12,Savana,Douglas",
            "13,William,Mitchell",
            "14,Ada,Smith",
            "15,Steven,Taylor",
            "16,Amy,Roberts",
            "17,Catherine,Henderson",
            "18,Ellia,Owens",
            "19,Dainton,Richardson",
            "20,Martin,Foster",
            "21,Caroline,Mason",
            "22,Audrey,Elliott",
            "23,Victoria,Walker",
            "24,Tony,Ellis",
            "25,Adrian,Sullivan",
            "26,Sawyer,Barnes",
            "27,Emma,Bailey",
            "28,Aiden,Jones",
            "29,Victoria,Fowler",
            "30,Elian,Allen",
            "31,Jasmine,Elliott",
            "32,Frederick,Crawford",
            "33,April,Cooper",
            "34,Lilianna,Payne",
            "35,Nicholas,Anderson",
            "36,Emily,Morris",
            "37,Adelaide,Edwards",
            "38,Rosie,Scott",
            "39,Lyndon,West",
            "40,Owen,Phillips",
            "41,Miller,Thomas",
            "42,Jared,Barnes",
            "43,Harold,Johnson",
            "44,Briony,Edwards",
            "45,Adrian,Cole",
            "46,Harold,Phillips",
            "47,David,Cooper",
            "48,Evelyn,Richards",
            "49,Roman,Taylor",
            "50,Dale,Thomas",
            "51,Oscar,Robinson",
            "52,Audrey,Crawford",
            "53,Oscar,Johnson",
            "54,Paige,Brown",
            "55,Fenton,Ross",
            "56,Rosie,Hawkins",
            "57,Adison,Williams",
            "58,Melissa,Baker",
            "59,Daniel,West",
            "60,Michelle,Johnston",
            "61,Roland,Edwards",
            "62,Anna,Craig",
            "63,Mary,Smith",
            "64,Frederick,Nelson",
            "65,Mike,Morgan",
            "66,Brooke,Tucker",
            "67,Amber,Parker",
            "68,Alford,Mitchell",
            "69,Adrianna,Thompson",
            "70,Tyler,Russell",
            "71,Jared,Brown",
            "72,Alford,Higgins",
            "73,Natalie,Cameron",
            "74,Darcy,Martin",
            "75,Kimberly,Spencer",
            "76,Max,Mason",
            "77,Ned,Higgins",
            "78,Elise,Cole",
            "79,Lucia,Brown",
            "80,Isabella,Armstrong",
            "81,Dale,Evans",
            "82,Ada,Carroll",
            "83,Arthur,Anderson",
            "84,Edgar,Perkins",
            "85,Adrian,Evans",
            "86,Dainton,Myers",
            "87,Roland,Higgins",
            "88,James,Robinson",
            "89,Abraham,Cooper",
            "90,Olivia,Turner",
            "91,Grace,Brooks",
            "92,Sydney,Casey",
            "93,Paige,Ryan",
            "94,Ashton,Ferguson",
            "95,Honey,Elliott",
            "96,Haris,Reed",
            "97,Walter,Morris",
            "98,Fenton,Miller",
            "99,Wilson,Wright",
            "100,Jasmine,Harris",
            "101,Brad,Elliott",
            "102,Vincent,Foster",
            "103,Daniel,Phillips",
            "104,Audrey,Ross",
            "105,Albert,Farrell",
            "106,Edwin,Roberts",
            "107,Valeria,Allen",
            "108,Lucy,Watson",
            "109,Paige,Bailey",
            "110,Miller,Parker",
            "111,Connie,Tucker",
            "112,Dale,Adams",
            "113,Derek,Morrison",
            "114,Jasmine,Evans",
            "115,Wilson,Carter",
            "116,Andrew,Hawkins",
            "117,Rebecca,Parker",
            "118,Anna,Cameron",
            "119,Miranda,Payne",
            "120,Carina,Turner",
            "121,Rafael,Barrett",
            "122,Martin,Ross",
            "123,Garry,Foster",
            "124,Ted,Warren",
            "125,Reid,Russell",
            "126,Miley,Smith",
            "127,Michael,Stewart",
            "128,Briony,Wells",
            "129,William,Armstrong",
            "130,Valeria,Clark",
            "131,Frederick,Cunningham",
            "132,Paul,Gray",
            "133,Ada,Crawford",
            "134,Harold,Cunningham",
            "135,Elian,Sullivan",
            "136,Carina,Rogers",
            "137,Jared,Sullivan",
            "138,Luke,Campbell",
            "139,James,Hamilton",
            "140,Vanessa,Martin",
            "141,Gianna,Barnes",
            "142,Melanie,Ryan",
            "143,Steven,Harrison",
            "144,Vanessa,Fowler",
            "145,Adison,Morrison",
            "146,Audrey,Scott",
            "147,Grace,Carter",
            "148,Robert,Foster",
            "149,Carina,Ellis",
            "150,Dainton,Kelley",
            "151,April,Sullivan",
            "152,Daisy,Cameron",
            "153,Lucy,Richards",
            "154,Evelyn,Brooks",
            "155,Amanda,Andrews",
            "156,Alissa,Taylor",
            "157,Sarah,Myers",
            "158,Aldus,Bennett",
            "159,Jared,Gray",
            "160,Victoria,Harris",
            "161,Nicole,Harrison",
            "162,Abigail,Fowler",
            "163,Brooke,Chapman",
            "164,Garry,Richardson",
            "165,Emily,Ferguson",
            "166,Tyler,Turner",
            "167,Aiden,Adams",
            "168,Harold,Jones",
            "169,Lucy,Bennett",
            "170,Dale,Tucker",
            "171,Lilianna,Miller",
            "172,Melissa,Hawkins",
            "173,Tiana,Johnson",
            "174,Julian,Harrison",
            "175,Samantha,Murray",
            "176,Roland,Phillips",
            "177,Miller,Andrews",
            "178,Sophia,Carter",
            "179,Anna,Kelley",
            "180,Victor,Hall",
            "181,Abraham,Gibson",
            "182,Arthur,Adams",
            "183,Dainton,Jones",
            "184,Nicholas,Hamilton",
            "185,Lyndon,Ellis",
            "186,Vanessa,Johnston",
            "187,Olivia,Bennett",
            "188,Alen,Richards",
            "189,Nicholas,Martin",
            "190,Cherry,Grant",
            "191,Justin,Carroll",
            "192,Walter,Harper",
            "193,Alissa,Howard",
            "194,Carlos,Ryan",
            "195,Arnold,Bennett",
            "196,Julia,Watson",
            "197,Eleanor,Carroll",
            "198,Aida,Foster",
            "199,Abraham,Douglas",
            "200,Vanessa,Andrews",
            "201,Rosie,Morrison",
            "202,Tony,Smith",
            "203,Martin,Jones",
            "204,Kellan,Grant",
            "205,Luke,Phillips",
            "206,Kelvin,Tucker",
            "207,Aston,Higgins",
            "208,Jacob,Holmes",
            "209,Lana,Henderson",
            "210,Caroline,Harris",
            "211,Richard,Hall",
            "212,Paige,Howard",
            "213,George,Brown",
            "214,Julian,Foster",
            "215,Amy,Murphy",
            "216,Amber,Howard",
            "217,Annabella,Allen",
            "218,Melissa,Taylor",
            "219,Charlie,Tucker",
            "220,Tony,Tucker",
            "221,Kelvin,Cooper",
            "222,Vincent,Richardson",
            "223,Sophia,Hall",
            "224,Charlie,Sullivan",
            "225,Daisy,Hamilton",
            "226,Alfred,Cooper",
            "227,Paige,Parker",
            "228,Kelvin,Harper",
            "229,Eric,Nelson",
            "230,Vivian,Tucker",
            "231,Sienna,Craig",
            "232,Roland,Howard",
            "233,Tony,Fowler",
            "234,Ned,Bailey",
            "235,Cherry,Ellis",
            "236,Valeria,Hamilton",
            "237,Daisy,Taylor",
            "238,Alisa,Edwards",
            "239,Victoria,Grant",
            "240,Edith,Cameron",
            "241,Patrick,Warren",
            "242,Oscar,Thompson",
            "243,Gianna,Davis",
            "244,Alina,Cole",
            "245,Clark,Roberts",
            "246,Stuart,Crawford",
            "247,Alen,Spencer",
            "248,Tess,Reed",
            "249,Agata,Murray",
            "250,Ryan,Cunningham",
            "251,Alisa,Phillips",
            "252,Marcus,Carroll",
            "253,Heather,Perkins",
            "254,Nicholas,Jones",
            "255,Eleanor,Wilson",
            "256,Leonardo,Hamilton",
            "257,Adele,Baker",
            "258,Elise,Parker",
            "259,Kate,Casey",
            "260,Nicholas,Harrison",
            "261,Tiana,Gibson",
            "262,Arianna,Higgins",
            "263,Thomas,Andrews",
            "264,Charlie,Payne",
            "265,Ryan,Thompson",
            "266,Charlotte,Hawkins",
            "267,Alford,Smith",
            "268,Jacob,Kelley",
            "269,Edward,Kelley",
            "270,Haris,Morrison",
            "271,Edith,Morris",
            "272,Kirsten,Tucker",
            "273,Hailey,Carroll",
            "274,Chelsea,Mason",
            "275,Amelia,Wells",
            "276,Steven,Howard",
            "277,Ted,Robinson",
            "278,Vivian,Evans",
            "279,Walter,Hunt",
            "280,Amanda,Mason",
            "281,Kelsey,Hawkins",
            "282,Brianna,Gibson",
            "283,Cadie,Ryan",
            "284,Alexander,Johnston",
            "285,Chester,Kelley",
            "286,Dale,Robinson",
            "287,Maria,Miller",
            "288,Kevin,Anderson",
            "289,Daisy,Stevens",
            "290,Audrey,Perry",
            "291,James,Dixon",
            "292,Belinda,Barnes",
            "293,Deanna,Scott",
            "294,Justin,Martin",
            "295,Lyndon,Cameron",
            "296,Adele,Hunt",
            "297,Deanna,Tucker",
            "298,David,Reed",
            "299,Rafael,Ferguson",
            "300,Ellia,Brown",
            "301,Brooke,Grant",
            "302,Florrie,Thompson",
            "303,Dominik,Gray",
            "304,Alissa,Douglas",
            "305,Michelle,Mitchell",
            "306,Anna,Owens",
            "307,Tara,Thomas",
            "308,Stuart,Phillips",
            "309,Daisy,Hawkins",
            "310,Lucia,Owens",
            "311,Tyler,Harrison",
            "312,Henry,Andrews",
            "313,Lilianna,Hill",
            "314,Vanessa,Morrison",
            "315,Tess,Gray",
            "316,Adrianna,Kelly",
            "317,Grace,Hawkins",
            "318,Edward,Rogers",
            "319,Adrian,Taylor",
            "320,Jessica,Casey",
            "321,Amanda,Hawkins",
            "322,Dexter,West",
            "323,Tyler,Stewart",
            "324,Adelaide,Dixon",
            "325,Grace,Cunningham",
            "326,Garry,Farrell",
            "327,Miranda,Jones",
            "328,Natalie,Morgan",
            "329,Jared,Russell",
            "330,Kate,Gibson",
            "331,Alen,Barrett",
            "332,Kelsey,Campbell",
            "333,Valeria,Russell",
            "334,Heather,Murray",
            "335,Jordan,Andrews",
            "336,Brad,Brooks",
            "337,Ellia,Andrews",
            "338,Charlie,Craig",
            "339,Sarah,Owens",
            "340,Edwin,Richards",
            "341,Paul,Henderson",
            "342,Emily,Reed",
            "343,Nicole,Lloyd",
            "344,Natalie,Farrell",
            "345,Brad,Scott",
            "346,Alisa,Cameron",
            "347,Steven,Nelson",
            "348,Lucy,Ellis",
            "349,Alissa,Morris",
            "350,Aston,Brown",
            "351,Roman,Craig",
            "352,Abraham,Hawkins",
            "353,Chloe,Martin",
            "354,Adrianna,Casey",
            "355,Melissa,Rogers",
            "356,Walter,Douglas",
            "357,Elian,Evans",
            "358,Alberta,Owens",
            "359,Maximilian,Brown",
            "360,Eleanor,Brooks",
            "361,Kimberly,Stevens",
            "362,Dominik,Harris",
            "363,Tiana,Cameron",
            "364,Michael,Casey",
            "365,Julian,Grant",
            "366,Lilianna,Andrews",
            "367,Aiden,Baker",
            "368,Joyce,Ellis",
            "369,Alfred,Williams",
            "370,Alen,Russell",
            "371,Ellia,Moore",
            "372,Michael,Mitchell",
            "373,Dominik,Evans",
            "374,Spike,Murray",
            "375,Mary,Moore",
            "376,Harold,Edwards",
            "377,John,Fowler",
            "378,Aiden,Taylor",
            "379,Henry,Chapman",
            "380,Dexter,Ferguson",
            "381,Belinda,Morrison",
            "382,Owen,Adams",
            "383,Elian,Scott",
            "384,Cadie,Perry",
            "385,Lydia,Barnes",
            "386,Edward,Allen",
            "387,Evelyn,Farrell",
            "388,Daryl,Dixon",
            "389,Charlotte,Russell",
            "390,Leonardo,Payne",
            "391,Chester,Morrison",
            "392,Savana,Evans",
            "393,Dainton,Reed",
            "394,Honey,Rogers",
            "395,Emily,Harper",
            "396,Stella,Edwards",
            "397,Freddie,Turner",
            "398,Aiden,Lloyd",
            "399,Kevin,Myers",
            "400,Richard,Harris",
            "401,Alfred,Armstrong",
            "402,Lana,Edwards",
            "403,Savana,Higgins",
            "404,Sienna,Evans",
            "405,David,Barnes",
            "406,Derek,Brooks",
            "407,Paul,Thompson",
            "408,Ted,Elliott",
            "409,Sophia,Howard",
            "410,Garry,Spencer",
            "411,Sabrina,Harrison",
            "412,Abraham,Mitchell",
            "413,Camila,Johnston",
            "414,Mike,Morris",
            "415,Tony,Wells",
            "416,Luke,Gibson",
            "417,Audrey,Evans",
            "418,Chelsea,Morris",
            "419,Vivian,Mason",
            "420,Amanda,Harris",
            "421,Alford,Wells",
            "422,Richard,Taylor",
            "423,Edward,Dixon",
            "424,Anna,Howard",
            "425,Victor,Hill",
            "426,Antony,Russell",
            "427,Rafael,Mitchell",
            "428,Vincent,Elliott",
            "429,Sydney,Riley",
            "430,Valeria,Perkins",
            "431,Cherry,Russell",
            "432,Patrick,Ellis",
            "433,Kate,Johnston",
            "434,Derek,Douglas",
            "435,Fenton,Gray",
            "436,Fenton,Richardson",
            "437,Victor,Ellis",
            "438,Carlos,Brooks",
            "439,Carl,Bailey",
            "440,Eleanor,Taylor",
            "441,Derek,Owens",
            "442,Alisa,Roberts",
            "443,William,Ross",
            "444,Preston,Cunningham",
            "445,Lydia,Reed",
            "446,Vanessa,Crawford",
            "447,Myra,Walker",
            "448,Brianna,Scott",
            "449,Sawyer,Turner",
            "450,Marcus,Howard",
            "451,Tess,Mason",
            "452,Mike,West",
            "453,Lucy,Myers",
            "454,Reid,Bennett",
            "455,Michelle,Turner",
            "456,Daniel,Russell",
            "457,Byron,Reed",
            "458,Leonardo,Murphy",
            "459,Amelia,Harper",
            "460,Dainton,Johnston",
            "461,Frederick,Johnston",
            "462,Robert,Hall",
            "463,Connie,Harrison",
            "464,Miller,Higgins",
            "465,Spike,Smith",
            "466,Briony,Lloyd",
            "467,Patrick,Morrison",
            "468,Annabella,Cameron",
            "469,Roland,Kelly",
            "470,Jordan,Kelley",
            "471,Jacob,Johnston",
            "472,Victoria,Cooper",
            "473,David,Phillips",
            "474,Alan,Reed",
            "475,Naomi,Gibson",
            "476,Alberta,Anderson",
            "477,Arthur,Campbell",
            "478,Florrie,Bailey",
            "479,Vanessa,Holmes",
            "480,Vivian,Russell",
            "481,Daisy,Mitchell",
            "482,Tara,Williams",
            "483,Wilson,Phillips",
            "484,Miller,Craig",
            "485,Lana,Barnes",
            "486,Alina,West",
            "487,Adele,Smith",
            "488,Aston,Johnson",
            "489,Spike,Carter",
            "490,Adison,Thomas",
            "491,Aida,Owens",
            "492,Michael,Rogers",
            "493,Rosie,Williams",
            "494,Roman,Mason",
            "495,Henry,Crawford",
            "496,Adrian,Thomas",
            "497,Ryan,Mitchell",
            "498,Maya,Howard",
            "499,April,Carter",
            "500,Arnold,Hunt",
            "501,Oscar,Craig",
            "502,Honey,Harper",
            "503,Dominik,Howard",
            "504,Lily,Moore",
            "505,Miley,Warren",
            "506,Daryl,Owens",
            "507,Elise,Hill",
            "508,Madaline,Henderson",
            "509,Elise,Ferguson",
            "510,Evelyn,Allen",
            "511,Preston,Sullivan",
            "512,Rubie,Clark",
            "513,Maria,Riley",
            "514,Ryan,Riley",
            "515,Ada,Parker",
            "516,Cherry,Montgomery",
            "517,James,Hill",
            "518,Walter,Watson",
            "519,Arnold,Chapman",
            "520,Emma,Martin",
            "521,Belinda,Cameron",
            "522,Amber,Ferguson",
            "523,Amanda,Howard",
            "524,Michelle,Nelson",
            "525,Jack,Warren",
            "526,Alexia,Douglas",
            "527,Daryl,Turner",
            "528,Ned,Miller",
            "529,Agata,Cunningham",
            "530,Connie,Andrews",
            "531,Max,Kelley",
            "532,Samantha,Turner",
            "533,Edward,Carroll",
            "534,Nicholas,Murphy",
            "535,Edith,Wilson",
            "536,Savana,Turner",
            "537,Adrianna,Miller",
            "538,Tyler,Baker",
            "539,Maya,Parker",
            "540,Harold,Sullivan",
            "541,Alissa,Cameron",
            "542,Owen,Tucker",
            "543,Darcy,Gray",
            "544,Blake,Riley",
            "545,Carlos,Miller",
            "546,Adison,Montgomery",
            "547,Lydia,Brooks",
            "548,Steven,Wilson",
            "549,Rebecca,Scott",
            "550,Roman,Russell",
            "551,Myra,Miller",
            "552,Ryan,Allen",
            "553,Ryan,Barrett",
            "554,Samantha,Cameron",
            "555,Vivian,Richards",
            "556,Garry,Barrett",
            "557,Jacob,Elliott",
            "558,Haris,Douglas",
            "559,Alissa,Brooks",
            "560,Owen,Cunningham",
            "561,Jack,Harris",
            "562,Oliver,Adams",
            "563,Vanessa,Wright",
            "564,Valeria,Nelson",
            "565,Anna,Gibson",
            "566,Harold,Perkins",
            "567,Isabella,Foster",
            "568,Adelaide,Owens",
            "569,Grace,Armstrong",
            "570,Roland,Dixon",
            "571,Camila,Ferguson",
            "572,Patrick,Montgomery",
            "573,Michael,Robinson",
            "574,Cherry,Casey",
            "575,Lana,Watson",
            "576,Stella,Richardson",
            "577,Penelope,Rogers",
            "578,Daryl,Farrell",
            "579,Aldus,Cooper",
            "580,Adele,Henderson",
            "581,Dainton,Alexander",
            "582,Brianna,Ellis",
            "583,Alberta,Holmes",
            "584,Patrick,Gray",
            "585,Chelsea,Hawkins",
            "586,Derek,Morris",
            "587,Evelyn,Tucker",
            "588,Jasmine,Riley",
            "589,Sophia,Montgomery",
            "590,Alfred,Stevens",
            "591,Miley,Anderson",
            "592,David,Russell",
            "593,Kevin,Richardson",
            "594,Julian,Morgan",
            "595,Ned,Johnson",
            "596,Amanda,Higgins",
            "597,Mike,Kelley",
            "598,David,Sullivan",
            "599,Spike,Davis",
            "600,Miranda,Craig",
            "601,Amy,Russell",
            "602,Kirsten,Johnson",
            "603,Brianna,Walker",
            "604,Roman,Barrett",
            "605,Jessica,Russell",
            "606,Lydia,Carter",
            "607,Lucy,Baker",
            "608,Oliver,Richards",
            "609,Blake,Scott",
            "610,Tara,Hamilton",
            "611,Kelsey,Bailey",
            "612,Carina,Elliott",
            "613,Brad,Farrell",
            "614,Owen,Gibson",
            "615,Fiona,Phillips",
            "616,Elise,Evans",
            "617,Kelvin,Fowler",
            "618,Joyce,Phillips",
            "619,Natalie,Wilson",
            "620,Clark,Grant",
            "621,Michael,Ross",
            "622,Maya,Clark",
            "623,Vanessa,Murray",
            "624,Kelvin,Lloyd",
            "625,Kevin,Carter",
            "626,Joyce,Bailey",
            "627,Brooke,Gibson",
            "628,Ryan,Henderson",
            "629,Lucas,Evans",
            "630,Alford,Carter",
            "631,Tara,Cole",
            "632,Adelaide,Murphy",
            "633,George,Harrison",
            "634,Annabella,Crawford",
            "635,Paul,Ellis",
            "636,Paige,Armstrong",
            "637,Audrey,Robinson",
            "638,Bruce,Harper",
            "639,Olivia,Harper",
            "640,Deanna,Armstrong",
            "641,Albert,Robinson",
            "642,Andrew,Richards",
            "643,Catherine,Howard",
            "644,Edward,Barnes",
            "645,Arnold,Harrison",
            "646,Nicole,Perry",
            "647,Arnold,Taylor",
            "648,Lilianna,Johnston",
            "649,Ellia,Cole",
            "650,Brad,Reed",
            "651,Briony,Ellis",
            "652,James,Hall",
            "653,Sabrina,Cole",
            "654,Maximilian,Lloyd",
            "655,Tess,Harris",
            "656,Rafael,Lloyd",
            "657,Lilianna,Casey",
            "658,Adelaide,Reed",
            "659,Daniel,Cooper",
            "660,Sam,Walker",
            "661,Eddy,Cameron",
            "662,Melissa,Mason",
            "663,Ned,Andrews",
            "664,Nicole,Ellis",
            "665,Brooke,Bennett",
            "666,Jenna,Andrews",
            "667,Anna,Dixon",
            "668,Ted,Bailey",
            "669,Marcus,Crawford",
            "670,Violet,Hawkins",
            "671,Sofia,Richardson",
            "672,Adam,Evans",
            "673,Natalie,Walker",
            "674,Jack,Miller",
            "675,Aston,Nelson",
            "676,John,Kelly",
            "677,Melanie,Douglas",
            "678,Henry,Moore",
            "679,Maximilian,Richards",
            "680,Daryl,Murphy",
            "681,Lily,Allen",
            "682,Martin,Roberts",
            "683,Tyler,Perkins",
            "684,Spike,Perry",
            "685,Jasmine,Lloyd",
            "686,Alexander,Taylor",
            "687,Melissa,Jones",
            "688,Nicholas,Cunningham",
            "689,Sofia,Miller",
            "690,Elise,Owens",
            "691,Aston,Grant",
            "692,Chester,Wells",
            "693,Arianna,Grant",
            "694,Clark,Hill",
            "695,Brooke,Stewart",
            "696,Kirsten,Montgomery",
            "697,Charlie,Bailey",
            "698,Andrew,Mason",
            "699,Alina,Grant",
            "700,Paul,Wilson",
            "701,Rubie,Farrell",
            "702,Andrew,Grant",
            "703,Stella,Watson",
            "704,Grace,Hamilton",
            "705,Melanie,Stewart",
            "706,Fiona,Nelson",
            "707,Leonardo,Hunt",
            "708,Elian,Stewart",
            "709,Wilson,Stevens",
            "710,Lucas,Barnes",
            "711,Martin,Harris",
            "712,Valeria,Bennett",
            "713,Thomas,Elliott",
            "714,Walter,Bailey",
            "715,Gianna,Henderson",
            "716,Andrew,Martin",
            "717,Henry,Kelly",
            "718,Miranda,Howard",
            "719,Tiana,Carter",
            "720,Edward,Mason",
            "721,James,Casey",
            "722,Arthur,Craig",
            "723,John,Henderson",
            "724,Heather,Kelly",
            "725,Edwin,Ross",
            "726,Edith,Perkins",
            "727,Evelyn,Perkins",
            "728,Olivia,Howard",
            "729,Edith,Cooper",
            "730,Ted,Campbell",
            "731,Kellan,Allen",
            "732,Deanna,Evans",
            "733,Kimberly,Morris",
            "734,Michael,Taylor",
            "735,Penelope,Gibson",
            "736,Abigail,Harris",
            "737,Naomi,Morrison",
            "738,Natalie,Grant",
            "739,Steven,Grant",
            "740,Rafael,Stewart",
            "741,Eddy,Watson",
            "742,Lily,Howard",
            "743,Audrey,Hill",
            "744,Arthur,Grant",
            "745,Melissa,Cooper",
            "746,Derek,Harris",
            "747,Tess,Baker",
            "748,Abigail,Grant",
            "749,Stella,Wright",
            "750,Jenna,Elliott",
            "751,Mary,Wright",
            "752,Annabella,Ross",
            "753,Dainton,Farrell",
            "754,Amy,Harrison",
            "755,Carina,Harper",
            "756,Maya,Turner",
            "757,Alan,Richardson",
            "758,Samantha,Ellis",
            "759,David,Hamilton",
            "760,Adrian,Barnes",
            "761,Maria,Richards",
            "762,Alen,Holmes",
            "763,Jacob,Barrett",
            "764,Frederick,Davis",
            "765,Carlos,Morris",
            "766,David,Davis",
            "767,Wilson,Higgins",
            "768,Ada,Harper",
            "769,Kevin,Howard",
            "770,Natalie,Hall",
            "771,Robert,Evans",
            "772,Preston,Crawford",
            "773,Belinda,Phillips",
            "774,April,Carroll",
            "775,Chester,Russell",
            "776,Sam,Davis",
            "777,Julian,Stewart",
            "778,Alfred,Owens",
            "779,Roman,Perry",
            "780,Maya,Stevens",
            "781,Honey,Thompson",
            "782,Camila,Carter",
            "783,Julian,Barrett",
            "784,Eddy,Montgomery",
            "785,Frederick,Andrews",
            "786,David,Spencer",
            "787,Preston,Ellis",
            "788,April,Thompson",
            "789,Victor,Murphy",
            "790,Audrey,Harrison",
            "791,Nicole,Brooks",
            "792,Martin,Chapman",
            "793,Brad,Sullivan",
            "794,Mary,Hawkins",
            "795,Camila,Turner",
            "796,Honey,Montgomery",
            "797,Stuart,Baker",
            "798,Roman,Johnston",
            "799,Melanie,Cole",
            "800,Deanna,Wright",
            "801,Tess,Myers",
            "802,Kevin,Hall",
            "803,Connie,Sullivan",
            "804,Carina,Miller",
            "805,Gianna,Evans",
            "806,Edith,Richardson",
            "807,Carl,Moore",
            "808,Emma,Montgomery",
            "809,Alfred,Hamilton",
            "810,Sofia,Owens",
            "811,Isabella,Cooper",
            "812,Aida,Russell",
            "813,Roland,Alexander",
            "814,Daniel,Miller",
            "815,Ned,Clark",
            "816,John,Ryan",
            "817,Naomi,Evans",
            "818,Robert,Adams",
            "819,Dexter,Warren",
            "820,George,Elliott",
            "821,Belinda,Harrison",
            "822,Hailey,Carter",
            "823,Aston,Morris",
            "824,Blake,Wells",
            "825,Julia,Hill",
            "826,Sophia,Smith",
            "827,Lana,Rogers",
            "828,Kevin,Davis",
            "829,Deanna,Myers",
            "830,Nicole,Stevens",
            "831,Emily,Elliott",
            "832,Oliver,Thomas",
            "833,Alina,Baker",
            "834,Amanda,Cunningham",
            "835,Steven,Stevens",
            "836,Freddie,Ross",
            "837,Kellan,Cole",
            "838,Roland,Craig",
            "839,Derek,Spencer",
            "840,Tess,Owens",
            "841,Joyce,Morgan",
            "842,Amber,Montgomery",
            "843,Ned,Hawkins",
            "844,Bruce,Casey",
            "845,Jasmine,Tucker",
            "846,Sienna,Foster",
            "847,Lucy,Thompson",
            "848,Ryan,Hamilton",
            "849,Adam,Perry",
            "850,Paige,Johnston",
            "851,Samantha,Harris",
            "852,Alina,Richards",
            "853,Adele,Craig",
            "854,Alissa,Richardson",
            "855,Florrie,Payne",
            "856,Gianna,Adams",
            "857,Oliver,Kelley",
            "858,Miller,Cunningham",
            "859,Michael,Russell",
            "860,Maya,Lloyd",
            "861,Melissa,Stewart",
            "862,Briony,Parker",
            "863,Julian,Watson",
            "864,Tara,Ryan",
            "865,Connie,Ellis",
            "866,Amy,Carter",
            "867,Alberta,Stewart",
            "868,Dexter,Johnson",
            "869,Natalie,Russell",
            "870,Chloe,Elliott",
            "871,Aston,Morrison",
            "872,Tony,Lloyd",
            "873,Oscar,Barrett",
            "874,Miller,Brown",
            "875,Jordan,Johnston",
            "876,Mary,Morgan",
            "877,Frederick,Martin",
            "878,Alisa,Baker",
            "879,Wilson,Harrison",
            "880,Maria,Craig",
            "881,David,Moore",
            "882,Julia,Carter",
            "883,Alen,Williams",
            "884,Edward,Johnson",
            "885,Abigail,Ryan",
            "886,Tyler,Nelson",
            "887,Alexander,Douglas",
            "888,April,Martin",
            "889,Lenny,Adams",
            "890,Caroline,Casey",
            "891,Sabrina,Owens",
            "892,Dale,Howard",
            "893,Amanda,Robinson",
            "894,Florrie,Owens",
            "895,Myra,Dixon",
            "896,Max,Thomas",
            "897,Maria,Stevens",
            "898,Robert,Lloyd",
            "899,Kimberly,Tucker",
            "900,Julia,Morris",
            "901,Aida,Clark",
            "902,Emma,Watson",
            "903,Kellan,Payne",
            "904,Clark,Ferguson",
            "905,Fenton,Reed",
            "906,Aiden,Carroll",
            "907,Bruce,Grant",
            "908,Adam,Cunningham",
            "909,Kate,Murray",
            "910,Sofia,Morris",
            "911,Brad,Fowler",
            "912,Joyce,Williams",
            "913,Victor,Craig",
            "914,Henry,Stevens",
            "915,Alen,Baker",
            "916,Sienna,Warren",
            "917,Amber,Owens",
            "918,Frederick,Cunningham",
            "919,Miley,Sullivan",
            "920,Alina,Allen",
            "921,Valeria,Evans",
            "922,Richard,Bailey",
            "923,Paul,Hawkins",
            "924,Bruce,Carroll",
            "925,Eleanor,Payne",
            "926,Honey,Russell",
            "927,Reid,Carroll",
            "928,Luke,Owens",
            "929,Penelope,Cameron",
            "930,Carlos,Campbell",
            "931,Rebecca,Morgan",
            "932,April,Ryan",
            "933,Sophia,Payne",
            "934,Amanda,Turner",
            "935,Kimberly,Craig",
            "936,Julian,Johnson",
            "937,Daryl,Carter",
            "938,Alen,Payne",
            "939,Cherry,Morris",
            "940,Wilson,Campbell",
            "941,Agata,Perry",
            "942,Maya,Gray",
            "943,Maddie,Lloyd",
            "944,Lydia,Richards",
            "945,Abigail,Edwards",
            "946,Oliver,Stewart",
            "947,Maximilian,Harper",
            "948,Aston,Cunningham",
            "949,Vincent,Roberts",
            "950,Patrick,Parker",
            "951,Charlie,Montgomery",
            "952,Jared,Ellis",
            "953,Ted,Thompson",
            "954,Lucia,Moore",
            "955,James,Barrett",
            "956,Henry,Howard",
            "957,Kimberly,Chapman",
            "958,Mike,Lloyd",
            "959,Naomi,Ellis",
            "960,Sienna,Payne",
            "961,Caroline,Perry",
            "962,Paul,Bailey",
            "963,Alexander,Evans",
            "964,Camila,Anderson",
            "965,Adrianna,Warren",
            "966,Ned,Taylor",
            "967,Maya,Ellis",
            "968,Aida,Hamilton",
            "969,Olivia,Morrison",
            "970,Jasmine,Casey",
            "971,Ellia,Riley",
            "972,Ada,Alexander",
            "973,Melissa,Parker",
            "974,Jasmine,Brown",
            "975,Penelope,Richards",
            "976,Adrian,Ellis",
            "977,Connie,Murphy",
            "978,Caroline,Crawford",
            "979,Victor,Ryan",
            "980,Edith,Lloyd",
            "981,Alexia,Phillips",
            "982,Justin,Hill",
            "983,Maria,Howard",
            "984,Emily,Jones",
            "985,Aida,Hill",
            "986,Melissa,Harper",
            "987,Rosie,Miller",
            "988,Vivian,Ellis",
            "989,Frederick,Moore",
            "990,Victoria,Turner",
            "991,Stuart,Perkins",
            "992,Melissa,Morris",
            "993,Jordan,Foster",
            "994,Chester,Alexander",
            "995,Marcus,Holmes",
            "996,Ada,Kelly",
            "997,Byron,Myers",
            "998,Sawyer,Jones",
            "999,Amelia,Allen",
            "1000,Arnold,Grant",
    };

    private String[] buildingData = {
            "1,Moortown Road",
            "2,Adel Square",
            "3,Roundhay Avenue",
            "4,Alwoodley Gardens",
            "5,Cookridge Court",
            "6,Tinshill Rise",
    } ;


}