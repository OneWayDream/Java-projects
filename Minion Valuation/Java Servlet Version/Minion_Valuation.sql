--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cookies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cookies (
    id bigint NOT NULL,
    user_id bigint,
    session_id uuid
);


ALTER TABLE public.cookies OWNER TO postgres;

--
-- Name: cookies_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cookies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cookies_id_seq OWNER TO postgres;

--
-- Name: cookies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cookies_id_seq OWNED BY public.cookies.id;


--
-- Name: fuels; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fuels (
    name character(30) NOT NULL,
    speed_boost integer
);


ALTER TABLE public.fuels OWNER TO postgres;

--
-- Name: item_bazaar_price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_bazaar_price (
    id bigint NOT NULL,
    item_name character(30),
    buy_price real,
    sell_price real
);


ALTER TABLE public.item_bazaar_price OWNER TO postgres;

--
-- Name: item_bazaar_price_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_bazaar_price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_bazaar_price_id_seq OWNER TO postgres;

--
-- Name: item_bazaar_price_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_bazaar_price_id_seq OWNED BY public.item_bazaar_price.id;


--
-- Name: item_compact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_compact (
    item_name character(30),
    result_name character(30),
    in_amount integer,
    out_amount integer
);


ALTER TABLE public.item_compact OWNER TO postgres;

--
-- Name: item_nps_price; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_nps_price (
    item_name character(30),
    price real
);


ALTER TABLE public.item_nps_price OWNER TO postgres;

--
-- Name: item_smelt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_smelt (
    item_name character(30),
    result_name character(30),
    in_amount integer,
    out_amount integer
);


ALTER TABLE public.item_smelt OWNER TO postgres;

--
-- Name: item_super_compact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_super_compact (
    item_name character(30),
    result_name character(30),
    in_amount integer,
    out_amount integer
);


ALTER TABLE public.item_super_compact OWNER TO postgres;

--
-- Name: minion_upgrade_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.minion_upgrade_group (
    minion_name character(30),
    upgrade_name character(30)
);


ALTER TABLE public.minion_upgrade_group OWNER TO postgres;

--
-- Name: minions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.minions (
    name character(30) NOT NULL,
    speed_1 real,
    speed_2 real,
    speed_3 real,
    speed_4 real,
    speed_5 real,
    speed_6 real,
    speed_7 real,
    speed_8 real,
    speed_9 real,
    speed_10 real,
    speed_11 real
);


ALTER TABLE public.minions OWNER TO postgres;

--
-- Name: production; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.production (
    minion_name character(30),
    item_name character(30),
    chance integer,
    amount real
);


ALTER TABLE public.production OWNER TO postgres;

--
-- Name: upgrades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.upgrades (
    name character(30) NOT NULL,
    upgrade_group character(30)
);


ALTER TABLE public.upgrades OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    login character(30),
    email character(30),
    password character(100),
    first_name character(30),
    gender integer,
    country character(30),
    nickname character(30),
    facebook character(30),
    vk character(30),
    role character(30)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: cookies id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cookies ALTER COLUMN id SET DEFAULT nextval('public.cookies_id_seq'::regclass);


--
-- Name: item_bazaar_price id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_bazaar_price ALTER COLUMN id SET DEFAULT nextval('public.item_bazaar_price_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: cookies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cookies (id, user_id, session_id) FROM stdin;
13	11	8bd03cb3-5901-4305-b050-31768cbbdd00
\.


--
-- Data for Name: fuels; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fuels (name, speed_boost) FROM stdin;
COAL                          	5
ENCHANTED_BREAD               	5
ENCHANTED_COAL                	10
ENCHANTED_CHARCOAL            	20
SOLAR_PANEL                   	25
ENCHANTED_LAVA_BUCKET         	25
MAGMA_BUCKET                  	30
PLASMA_BUCKET                 	35
HAMSTER_WHEEL                 	50
FOUL_FLESH                    	90
COAL_BLOCK                    	5
CATALYST                      	300
HYPER_CATALYST                	400
\.


--
-- Data for Name: item_bazaar_price; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_bazaar_price (id, item_name, buy_price, sell_price) FROM stdin;
20	ENCHANTED_BROWN_MUSHROOM      	1453	1751.2
21	INK_SACK:3                    	3.1	4.8
22	ENCHANTED_COCOA               	514.4	563.7
23	CACTUS                        	0.6	0.8
24	ENCHANTED_CACTUS_GREEN        	283.3	376.2
25	ENCHANTED_CACTUS              	55952	77591.6
26	SUGAR_CANE                    	2	2.7
27	ENCHANTED_SUGAR               	308.6	319.4
28	ENCHANTED_SUGAR_CANE          	51200.2	52495.4
29	RAW_BEEF                      	3.9	5.3
30	LEATHER                       	10.7	15
31	ENCHANTED_RAW_BEEF            	618.3	646.7
32	ENCHANTED_LEATHER             	6334.4	6564.4
33	PORK                          	9.1	14.6
34	ENCHANTED_PORK                	1043.6	1171.2
35	ENCHANTED_GRILLED_PORK        	125012.4	138885.8
37	ENCHANTED_EGG                 	699	1022.4
38	FEATHER                       	2.1	3.3
39	ENCHANTED_FEATHER             	459.9	479.4
40	RAW_CHICKEN                   	4.3	7.5
41	ENCHANTED_RAW_CHICKEN         	616.3	653
42	MUTTON                        	10.8	13.9
43	ENCHANTED_MUTTON              	1816.3	1889
44	ENCHANTED_COOKED_MUTTON       	125157.2	188887.7
46	ENCHANTED_RABBIT              	633.9	634.1
45	RABBIT                        	3.2	6
48	ENCHANTED_RABBIT_FOOT         	2836	3130.2
47	RABBIT_FOOT                   	15.6	17.2
49	RABBIT_HIDE                   	15.9	21.9
50	ENCHANTED_RABBIT_HIDE         	11450.7	13291.6
52	ENCHANTED_NETHER_STALK        	474.5	500.9
53	COBBLESTONE                   	6	6.9
54	ENCHANTED_COBBLESTONE         	1102.1	1133
55	COAL                          	6.6	7.7
56	ENCHANTED_COAL                	868	896
57	ENCHANTED_COAL_BLOCK          	137452.7	141843.9
58	IRON_INGOT                    	6.1	7.6
59	ENCHANTED_IRON                	1017.7	1105
60	ENCHANTED_IRON_BLOCK          	178000	187797.4
61	GOLD_INGOT                    	13.4	14
62	ENCHANTED_GOLD                	1678.1	1732.8
63	ENCHANTED_GOLD_BLOCK          	228620.4	234359.1
64	DIAMOND                       	7.7	8.5
65	ENCHANTED_DIAMOND             	1281.1	1281.7
66	ENCHANTED_DIAMOND_BLOCK       	204515.4	207608.4
67	INK_SACK:4                    	2.2	3
68	ENCHANTED_LAPIS_LAZULI        	420.9	437.6
69	ENCHANTED_LAPIS_LAZULI_BLOCK  	39588.3	41990.2
70	EMERALD                       	5.5	7.9
71	ENCHANTED_EMERALD             	955.2	999
72	ENCHANTED_EMERALD_BLOCK       	153402.1	165996
73	REDSTONE                      	1.2	1.8
74	ENCHANTED_REDSTONE            	226	246.3
75	ENCHANTED_REDSTONE_BLOCK      	33706.8	38097
76	QUARTZ                        	11.2	17
77	ENCHANTED_QUARTZ              	731.9	871.4
78	ENCHANTED_QUARTZ_BLOCK        	113957.8	129511.7
79	OBSIDIAN                      	11.7	13.7
80	ENCHANTED_OBSIDIAN            	1919.1	2063.3
81	GLOWSTONE_DUST                	7.7	11.5
82	ENCHANTED_GLOWSTONE_DUST      	817.1	900.8
83	ENCHANTED_GLOWSTONE           	58600.2	63330.2
84	GRAVEL                        	1.3	2.4
85	FLINT                         	11.9	12.5
86	ENCHANTED_FLINT               	2001	2189.8
18	BROWN_MUSHROOM                	8.6	11.3
88	PACKED_ICE                    	26.7	28.9
89	ENCHANTED_ICE                 	538.4	571.7
90	ENCHANTED_PACKED_ICE          	89040.7	97265.9
91	SAND                          	7	9
92	ENCHANTED_SAND                	908.7	1138.7
93	ENDER_STONE                   	1.5	2.4
94	ENCHANTED_ENDSTONE            	320.2	344.9
95	CLAY_BALL                     	2.6	4
97	ROTTEN_FLESH                  	3.7	4.8
98	ENCHANTED_ROTTEN_FLESH        	685.5	713.8
99	BONE                          	2.7	5.2
100	ENCHANTED_BONE                	688.6	722.5
101	STRING                        	3.2	5.1
16	ENCHANTED_MELON_BLOCK         	25500	27636.8
102	ENCHANTED_STRING              	874.8	1030.7
103	SPIDER_EYE                    	3.5	6.2
104	ENCHANTED_SPIDER_EYE          	836.5	1264
105	SULPHUR                       	13	18.2
106	ENCHANTED_GUNPOWDER           	2201.4	2404.2
3	HAY_BLOCK                     	32.4	41.9
107	ENDER_PEARL                   	10.3	10.7
2	SEEDS                         	0.2	0.5
6	CARROT_ITEM                   	3.2	4.1
4	ENCHANTED_SEEDS               	80.6	92.8
8	POTATO_ITEM                   	2.5	4
9	ENCHANTED_POTATO              	269.6	414.3
10	ENCHANTED_BAKED_POTATO        	65700.1	68556
12	ENCHANTED_PUMPKIN             	620.3	645.7
11	PUMPKIN                       	3.5	4.4
15	ENCHANTED_MELON               	165	188.8
17	RED_MUSHROOM                  	11.8	14.4
7	ENCHANTED_CARROT              	506.3	523.6
13	MELON                         	2.3	7.8
19	ENCHANTED_RED_MUSHROOM        	2247.2	2652.3
114	ENCHANTED_BLAZE_POWDER        	1442.6	1769.6
140	PRISMARINE_CRYSTALS           	4.5	7.3
141	ENCHANTED_PRISMARINE_CRYSTALS 	387.9	399.1
142	PRISMARINE_SHARD              	2.4	5.2
143	ENCHANTED_PRISMARINE_SHARD    	387.6	401.7
144	SPONGE                        	473	520.9
145	ENCHANTED_SPONGE              	18284	20089.6
5	ENCHANTED_HAY_BLOCK           	5287.6	5519.9
51	NETHER_STALK                  	2.1	3.4
87	ICE                           	3.2	4.6
96	ENCHANTED_CLAY_BALL           	476.2	479.1
1	WHEAT                         	2.9	3.2
108	ENCHANTED_ENDER_PEARL         	193.2	199.9
109	GHAST_TEAR                    	26.4	29.9
127	ENCHANTED_SPRUCE_LOG          	466.2	535.1
128	ENCHANTED_JUNGLE_LOG          	740.3	791.3
129	SNOW_BALL                     	0.3	0.9
130	SNOW_BLOCK                    	7.8	12.2
131	ENCHANTED_SNOW_BLOCK          	596.3	603.9
132	RAW_FISH                      	19.1	23.6
133	ENCHANTED_RAW_FISH            	3352.1	3699
134	RAW_FISH:1                    	8.4	13
135	ENCHANTED_RAW_SALMON          	1573.2	1581.4
136	RAW_FISH:2                    	18	19.4
137	ENCHANTED_CLOWNFISH           	3126.5	3175.2
138	RAW_FISH:3                    	13	15.4
115	MAGMA_CREAM                   	7.8	10.8
116	ENCHANTED_MAGMA_CREAM         	1267.7	1297.3
117	LOG                           	4.5	7.1
118	LOG:1                         	1.6	3.2
119	LOG:2                         	3.1	4.1
120	LOG_2:1                       	1.7	2.7
121	LOG_2                         	2	3.2
122	LOG:3                         	4.4	5.9
123	ENCHANTED_OAK_LOG             	670.8	746.8
124	ENCHANTED_BIRCH_LOG           	551.4	643.2
125	ENCHANTED_ACACIA_LOG          	500.1	591.5
139	ENCHANTED_PUFFERFISH          	2377.1	2637.5
110	ENCHANTED_GHAST_TEAR          	144.1	168.7
111	SLIME_BALL                    	3.9	6.4
112	ENCHANTED_SLIME_BALL          	878.4	1053.6
113	BLAZE_ROD                     	15.8	19.3
126	ENCHANTED_DARK_OAK_LOG        	385.5	477.5
\.


--
-- Data for Name: item_compact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_compact (item_name, result_name, in_amount, out_amount) FROM stdin;
MELON                         	MELON_BLOCK                   	9	1
COAL                          	COAL_BLOCK                    	9	1
IRON_INGOT                    	IRON_BLOCK                    	9	1
GOLD_INGOT                    	GOLD_BLOCK                    	9	1
DIAMOND                       	DIAMOND_BLOCK                 	9	1
INK_SACK:4                    	LAPIS_LAZULI_BLOCK            	9	1
EMERALD                       	EMERALD_BLOCK                 	9	1
REDSTONE                      	REDSTONE_BLOCK                	9	1
QUARTZ                        	QUARTZ_BLOCK                  	4	1
GLOWSTONE_DUST                	GLOWSTONE_BLOCK               	4	1
ICE                           	PACKED_ICE                    	9	1
CLAY                          	CLAY_BLOCK                    	4	1
SLIME_BALL                    	SLIME_BALL_BLOCK              	9	1
SNOW_BALL                     	SNOW_BLOCK                    	9	1
WHEAT                         	HAY_BLOCK                     	9	1
\.


--
-- Data for Name: item_nps_price; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_nps_price (item_name, price) FROM stdin;
SEEDS                         	0.5
HAY_BLOCK                     	9
ENCHANTED_HAY_BLOCK           	1300
ENCHANTED_COCOA               	480
PORK                          	5
ENCHANTED_RABBIT              	640
RABBIT                        	4
RABBIT_FOOT                   	5
ENCHANTED_RABBIT_FOOT         	800
NETHER_STALK                  	3
ENCHANTED_NETHER_STALK        	480
ENCHANTED_POISONOUS_POTATO    	1600
POTATO_ITEM                   	1
CARROT_ITEM                   	1
IRON_ORE                      	3
GOLD_ORE                      	3
WHEAT                         	1
ENCHANTED_SEED                	80
ENCHANTED_BREAD               	60
ENCHANTED_CARROT              	160
ENCHANTED_POTATO              	160
ENCHANTED_BAKED_POTATO        	25600
PUMPKIN                       	4
ENCHANTED_PUMPKIN             	640
MELON                         	0.5
MELON_BLOCK                   	4.5
ENCHANTED_MELON               	160
ENCHANTED_MELON_BLOCK         	25600
RED_MUSHROOM                  	4
BROWN_MUSHROOM                	4
ENCHANTED_RED_MUSHROOM        	640
ENCHANTED_BROWN_MUSHROOM      	640
INK_SACK:3                    	3
CACTUS                        	1
CACTUS_GREEN                  	1
ENCHANTED_CACTUS_GREEN        	160
ENCHANTED_CACTUS              	25600
SUGAR_CANE                    	2
ENCHANTED_SUGAR               	320
ENCHANTED_SUGAR_CANE          	51200
RAW_BEEF                      	4
LEATHER                       	3
ENCHANTED_RAW_BEEF            	640
ENCHANTED_LEATHER             	1700
ENCHANTED_PORK                	800
ENCHANTED_GRILLED_PORK        	128000
EGG                           	3
ENCHANTED_EGG                 	432
FEATHER                       	3
ENCHANTED_FEATHER             	480
RAW_CHICKEN                   	4
ENCHANTED_RAW_CHICKEN         	640
MUTTON                        	5
ENCHANTED_MUTTON              	800
ENCHANTED_COOKED_MUTTON       	128000
WHITE_WOOL                    	2
ENCHANTED_WHITE_WOOL          	320
RABBIT_HIDE                   	5
ENCHANTED_RABBIT_HIDE         	2880
STONE                         	1
COBBLESTONE                   	1
ENCHANTED_COBBLESTONE         	160
COAL                          	2
ENCHANTED_COAL                	320
ENCHANTED_COAL_BLOCK          	51000
IRON_INGOT                    	3
ENCHANTED_IRON                	480
ENCHANTED_IRON_BLOCK          	76800
GOLD_INGOT                    	4
ENCHANTED_GOLD                	640
ENCHANTED_GOLD_BLOCK          	102400
DIAMOND                       	8
ENCHANTED_DIAMOND             	1280
ENCHANTED_DIAMOND_BLOCK       	204800
INK_SACK:4                    	1
ENCHANTED_LAPIS_LAZULI        	160
ENCHANTED_LAPIS_LAZULI_BLOCK  	25600
EMERALD                       	6
ENCHANTED_EMERALD             	960
ENCHANTED_EMERALD_BLOCK       	153600
REDSTONE                      	1
ENCHANTED_REDSTONE            	160
ENCHANTED_REDSTONE_BLOCK      	25600
QUARTZ                        	4
ENCHANTED_QUARTZ              	640
ENCHANTED_QUARTZ_BLOCK        	102400
OBSIDIAN                      	12
ENCHANTED_OBSIDIAN            	1920
GLOWSTONE_DUST                	2
ENCHANTED_GLOWSTONE_DUST      	320
GRAVEL                        	3
FLINT                         	4
ENCHANTED_FLINT               	640
ICE                           	0.5
PACKED_ICE                    	4.5
ENCHANTED_ICE                 	80
ENCHANTED_PACKED_ICE          	12800
SAND                          	2
ENCHANTED_SAND                	320
GLASS                         	2
ENDER_STONE                   	2
ENCHANTED_ENDSTONE            	320
CLAY                          	3
BRICK                         	2
ENCHANTED_CLAY                	480
ROTTEN_FLESH                  	2
ENCHANTED_ROTTEN_FLESH        	320
BONE                          	2
ENCHANTED_BONE                	320
STRING                        	3
ENCHANTED_STRING              	480
SPIDER_EYE                    	3
ENCHANTED_SPIDER_EYE          	480
ENCHANTED_GUNPOWDER           	640
ENDER_PEARL                   	10
ENCHANTED_ENDER_PEARL         	200
GHAST_TEAR                    	16
ENCHANTED_GHAST_TEAR          	80
SLIME_BALL                    	5
ENCHANTED_SLIME_BALL          	800
BLAZE_ROD                     	9
ENCHANTED_BLAZE_POWDER        	1440
MAGMA_CREAM                   	8
ENCHANTED_MAGMA_CREAM         	1280
LOG                           	2
LOG:1                         	2
LOG:2                         	2
LOG_2:1                       	2
LOG_2                         	2
LOG:3                         	2
ENCHANTED_OAK_LOG             	320
ENCHANTED_BIRCH_LOG           	320
ENCHANTED_ACACIA_LOG          	320
ENCHANTED_DARK_OAK_LOG        	320
ENCHANTED_SPRUCE_LOG          	320
ENCHANTED_JUNGLE_LOG          	320
SNOW_BALL                     	1
SNOW_BLOCK                    	4
ENCHANTED_SNOW_BLOCK          	600
RAW_FISH                      	6
ENCHANTED_RAW_FISH            	960
RAW_FISH:1                    	10
ENCHANTED_RAW_SALMON          	1600
RAW_FISH:2                    	20
ENCHANTED_PUFFERFISH          	2400
PRISMARINE_CRYSTALS           	5
ENCHANTED_PRISMARINE_CRYSTALS 	400
PRISMARINE_SHARD              	5
ENCHANTED_PRISMARINE_SHARD    	400
SPONGE                        	50
ENCHANTED_SPONGE              	2000
ENCHANTED_GLOWSTONE           	61000
SULPHUR                       	4
ENCHANTED_CLOWNFISH           	3200
RAW_FISH:3                    	15
POISONOUS_POTATO              	10
FLOWER                        	1
IRON_BLOCK                    	27
GOLD_BLOCK                    	36
DIAMOND_BLOCK                 	72
EMERALD_BLOCK                 	54
LAPIS_LAZULI_BLOCK            	9
REDSTONE_BLOCK                	9
GLOWSTONE_BLOCK               	8
QUARTZ_BLOCK                  	36
CLAY_BLOCK                    	12
SLIME_BALL_BLOCK              	45
\.


--
-- Data for Name: item_smelt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_smelt (item_name, result_name, in_amount, out_amount) FROM stdin;
CACTUS                        	CACTUS_GREEN                  	1	1
COBBLESTONE                   	STONE                         	1	1
IRON_ORE                      	IRON_INGOT                    	1	1
GOLD_ORE                      	GOLD_INGOT                    	1	1
SAND                          	GLASS                         	1	1
CLAY                          	BRICK                         	1	1
LOG                           	COAL                          	1	1
LOG:1                         	COAL                          	1	1
LOG:2                         	COAL                          	1	1
LOG:3                         	COAL                          	1	1
LOG_2                         	COAL                          	1	1
LOG_2:1                       	COAL                          	1	1
\.


--
-- Data for Name: item_super_compact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_super_compact (item_name, result_name, in_amount, out_amount) FROM stdin;
HAY_BLOCK                     	ENCHANTED_HAY_BLOCK           	144	1
CARROT_ITEM                   	ENCHANTED_CARROT              	160	1
POTATO_ITEM                   	ENCHANTED_POTATO              	160	1
INK_SACK:3                    	ENCHANTED_COCOA               	160	1
RABBIT                        	ENCHANTED_RABBIT              	160	1
NETHER_STALK                  	ENCHANTED_NETHER_STALK        	160	1
SULPHUR                       	ENCHANTED_GUNPOWDER           	160	1
IRON_INGOT                    	ENCHANTED_IRON                	160	1
SEEDS                         	ENCHANTED_SEEDS               	160	1
WHEAT                         	ENCHANTED_BREAD               	60	1
PUMPKIN                       	ENCHANTED_PUMPKIN             	160	1
MELON                         	ENCHANTED_MELON               	160	1
RED_MUSHROOM                  	ENCHANTED_RED_MUSHROOM        	160	1
BROWN_MUSHROOM                	ENCHANTED_BROWN_MUSHROOM      	160	1
CACTUS_GREEN                  	ENCHANTED_CACTUS_GREEN        	160	1
SUGAR_CANE                    	ENCHANTED_SUGAR               	160	1
RAW_BEEF                      	ENCHANTED_RAW_BEEF            	160	1
LEATHER                       	ENCHANTED_LEATHER             	576	1
PORK                          	ENCHANTED_PORK                	160	1
RAW_CHICKEN                   	ENCHANTED_RAW_CHICKEN         	160	1
FEATHER                       	ENCHANTED_FEATHER             	160	1
EGG                           	ENCHANTED_EGG                 	144	1
MUTTON                        	ENCHANTED_MUTTON              	160	1
WOOL                          	ENCHANTED_WOOL                	160	1
RABBIT_FOOT                   	ENCHANTED_RABBIT_FOOT         	60	1
RABBIT_HIDE                   	ENCHANTED_RABBIT_HIDE         	576	1
COBBLESTONE                   	ENCHANTED_COBBLESTONE         	160	1
COAL                          	ENCHANTED_COAL                	160	1
COAL_BLOCK                    	ENCHANTED_COAL                	160	9
DIAMOND                       	ENCHANTED_DIAMOND             	160	1
DIAMOND_BLOCK                 	ENCHANTED_DIAMOND             	160	9
INK_SACK:4                    	ENCHANTED_LAPIS_LAZULI        	160	1
LAPIS_LAZULI_BLOCK            	ENCHANTED_LAPIS_LAZULI        	160	9
EMERALD                       	ENCHANTED_EMERALD             	160	1
EMERALD_BLOCK                 	ENCHANTED_EMERALD             	160	9
REDSTONE                      	ENCHANTED_REDSTONE            	160	1
REDSTONE_BLOCK                	ENCHANTED_REDSTONE            	160	9
QUARTZ                        	ENCHANTED_QUARTZ              	160	1
QUARTZ_BLOCK                  	ENCHANTED_QUARTZ              	160	4
OBSIDIAN                      	ENCHANTED_OBSIDIAN            	160	1
GLOWSTONE_DUST                	ENCHANTED_GLOWSTONE_DUST      	160	1
GLOWSTONE_BLOCK               	ENCHANTED_GLOWSTONE           	160	4
FLINT                         	ENCHANTED_FLINT               	160	1
ICE                           	ENCHANTED_ICE                 	160	1
PACKED_ICE                    	ENCHANTED_ICE                 	160	9
SAND                          	ENCHANTED_SAND                	160	1
END_STONE                     	ENCHANTED_END_STONE           	144	1
CLAY                          	ENCHANTED_CLAY                	160	1
CLAY_BLOCK                    	ENCHANTED_CLAY                	160	4
ROTTEN_FLESH                  	ENCHANTED_ROTTEN_FLESH        	160	1
POISONOUS_POTATO              	ENCHANTED_POISONOUS_POTATO    	160	1
BONE                          	ENCHANTED_BONE                	160	1
STRING                        	ENCHANTED_STRING              	160	1
SPIDER_EYE                    	ENCHANTED_SPIDER_EYE          	160	1
ENDER_PEARL                   	ENCHANTED_ENDER_PEARL         	20	1
GHAST_TEAR                    	ENCHANTED_GHAST_TEAR          	5	1
SLIME_BALL                    	ENCHANTED_SLIME_BALL          	160	1
BLAZE_ROD                     	ENCHANTED_BLAZE_POWDER        	160	1
MAGMA_CREAM                   	ENCHANTED_MAGMA_CREAM         	160	1
LOG                           	ENCHANTED_OAK_LOG             	160	1
LOG:1                         	ENCHANTED_SPRUCE_LOG          	160	1
LOG:2                         	ENCHANTED_BIRCH_LOG           	160	1
ILOG_2:1                      	ENCHANTED_DARK_OAK_LOG        	160	1
LOG_2                         	ENCHANTED_ACACIA_LOG          	160	1
LOG:3                         	ENCHANTED_JUNGLE_OAK          	160	1
SNOW_BLOCK                    	ENCHANTED_SNOW_BLOCK          	160	1
FLOWER                        	ENCHANTED_FLOWER              	160	1
RAW_FISH                      	ENCHANTED_RAW_FISH            	160	1
RAW_FISH:1                    	ENCHANTED_RAW_SALMON          	160	1
RAW_FISH:2                    	ENCHANTED_CLOWNFISH           	160	1
RAW_FISH:3                    	ENCHANTED_PUFFERFISH          	160	1
PRISMARINE_SHARD              	ENCHANTED_PRISMARINE_SHARD    	80	1
PRISMARIN_CRYSTALS            	ENCHANTED_PRISMARIN_CRYSTALS  	80	1
SPONGE                        	ENCHANTED_SPONGE              	40	1
IRON_BLOCK                    	ENCHANTED_IRON                	160	9
GOLD_BLOCK                    	ENCHANTED_GOLD                	160	9
GOLD_INGOT                    	ENCHANTED_GOLD                	160	1
\.


--
-- Data for Name: minion_upgrade_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.minion_upgrade_group (minion_name, upgrade_name) FROM stdin;
DARK_OAK_MINION               	AUTO_SMELTER                  
GLOWSTONE_MINION              	COMPACTOR                     
GRAVEL_MINION                 	FLINT_SHOVEL                  
COBBLESTONE_MINION            	AUTO_SMELTER                  
EMERALD_MINION                	COMPACTOR                     
SAND_MINION                   	AUTO_SMELTER                  
IRON_MINION                   	COMPACTOR                     
ICE_MINION                    	COMPACTOR                     
GOLD_MINION                   	AUTO_SMELTER                  
CLAY_MINION                   	COMPACTOR                     
WHEAT_MINION                  	COMPACTOR                     
SLIME_MINION                  	COMPACTOR                     
DIAMOND_MINION                	COMPACTOR                     
CHICKEN_MINION                	ENCHANTED_EGG                 
OAK_MINION                    	AUTO_SMELTER                  
SPRUCE_MINION                 	AUTO_SMELTER                  
JUNGLE_MINION                 	AUTO_SMELTER                  
CACTUS_MINION                 	AUTO_SMELTER                  
GOLD_MINION                   	COMPACTOR                     
BIRCH_MINION                  	AUTO_SMELTER                  
ACACIA_MINION                 	AUTO_SMELTER                  
LAPIS_MINION                  	COMPACTOR                     
CLAY_MINION                   	AUTO_SMELTER                  
COAL_MINION                   	COMPACTOR                     
QUARTZ_MINION                 	COMPACTOR                     
REDSTONE_MINION               	COMPACTOR                     
IRON_MINION                   	COMPACTOR                     
SNOW_MINION                   	COMPACTOR                     
MELON_MINION                  	COMPACTOR                     
IRON_MINION                   	AUTO_SMELTER                  
\.


--
-- Data for Name: minions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.minions (name, speed_1, speed_2, speed_3, speed_4, speed_5, speed_6, speed_7, speed_8, speed_9, speed_10, speed_11) FROM stdin;
COBBLESTONE_MINION            	14	14	12	12	10	10	9	9	8	8	7
OBSIDIAN_MINION               	45	45	42	42	39	39	35	35	30	30	24
GLOWSTONE_MINION              	25	25	23	23	21	21	19	19	16	16	13
GRAVEL_MINION                 	26	26	24	24	22	22	19	19	16	16	13
SAND_MINION                   	26	26	24	24	22	22	19	19	16	16	13
CLAY_MINION                   	32	32	30	30	27.5	27.5	24	24	20	20	16
ICE_MINION                    	14	14	12	12	10	10	9	9	8	8	7
SNOW_MINION                   	13	13	12	12	11	11	9.5	9.5	8	8	6.5
COAL_MINION                   	15	15	13	13	12	12	10	10	9	9	7
IRON_MINION                   	17	17	15	15	14	14	12	12	10	10	8
GOLD_MINION                   	22	22	20	20	18	18	16	16	14	14	11
DIAMOND_MINION                	29	29	27	27	25	25	22	22	19	19	15
LAPIS_MINION                  	29	29	27	27	25	25	23	23	21	21	18
REDSTONE_MINION               	29	29	27	27	25	25	23	23	21	21	18
EMERALD_MINION                	28	28	26	26	24	24	21	21	18	18	14
QUARTZ_MINION                 	22.5	22.5	21	21	19	19	17	17	14.5	14.5	11.5
END_STONE_MINION              	26	26	24	24	22	22	19	19	16	16	13
WHEAT_MINION                  	15	15	13	13	11	11	10	10	9	9	8
MELON_MINION                  	24	24	22.5	22.5	21	21	18.5	18.5	16	16	13
PUMPKIN_MINION                	32	32	30	30	27.5	27.5	24	24	20	20	16
CARROT_MINION                 	20	20	18	18	16	16	14	14	12	12	10
POTATO_MINION                 	20	20	18	18	16	16	14	14	12	12	10
MUSHROOM_MINION               	30	30	28	28	26	26	23	23	20	20	16
CACTUS_MINION                 	27	27	25	25	23	23	21	21	18	18	15
COCOA_BEANS_MINION            	27	27	25	25	23	23	21	21	18	18	15
SUGAR_CANE_MINION             	22	22	20	20	18	18	16	16	14.5	14.5	12
NETHER_WARD_MINION            	50	50	47	47	44	44	41	41	38	38	32
FLOWER_MINION                 	30	29	28	27	26	25	24	23	22	20	18
FISHING_MINION                	78	75	72	72	68	68	62.5	62.5	53	53	35
ZOMBIE_MINION                 	26	26	24	24	22	22	20	20	17	17	13
REVENANT_MINION               	29	29	26	26	23	23	19	19	14.5	14.5	10
SKELETON_MINION               	26	26	24	24	22	22	20	20	17	17	13
CREEPER_MINION                	27	27	25	25	23	23	21	21	18	18	14
SPIDER_MINION                 	26	26	24	24	22	22	20	20	17	17	13
TARANTULA_MINION              	29	29	26	26	23	23	19	19	14.5	14.5	10
CAVE_SPIDER_MINION            	26	26	24	24	22	22	20	20	17	17	13
BLAZE_MINION                  	33	33	31	31	28.5	28.5	25	25	21	21	16.5
MAGMA_CUBE_MINION             	32	32	30	30	28	28	25	25	22	22	18
ENDERMAN_MINION               	32	32	30	30	28	28	25	25	22	22	18
GHAST_MINION                  	50	50	47	47	44	44	41	41	38	38	32
SLIME_MINION                  	26	26	24	24	22	22	19	19	16	16	12
COW_MINION                    	26	26	24	24	22	22	20	20	17	17	13
PIG_MINION                    	26	26	24	24	22	22	20	20	17	17	13
CHICKEN_MINION                	26	26	24	24	22	22	20	20	18	18	15
SHEEP_MINION                  	24	24	22	22	20	20	18	18	16	16	12
RABBIT_MINION                 	26	26	24	24	22	22	20	20	17	17	13
OAK_MINION                    	48	48	45	45	42	42	38	38	33	33	27
SPRUCE_MINION                 	48	48	45	45	42	42	38	38	33	33	27
BIRCH_MINION                  	48	48	45	45	42	42	38	38	33	33	27
DARK_OAK_MINION               	48	48	45	45	42	42	38	38	33	33	27
ACACIA_MINION                 	48	48	45	45	42	42	38	38	33	33	27
JUNGLE_MINION                 	48	48	45	45	42	42	38	38	33	33	27
\.


--
-- Data for Name: production; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.production (minion_name, item_name, chance, amount) FROM stdin;
WHEAT_MINION                  	WHEAT                         	100	1
WHEAT_MINION                  	SEEDS                         	100	1
PUMPKIN_MINION                	PUMPKIN                       	100	1
MELON_MINION                  	MELON                         	100	4.5
MUSHROOM_MINION               	BROWN_MUSHROOM                	50	1
MUSHROOM_MINION               	RED_MUSHROOM                  	50	1
COCOA_BEANS_MINION            	INK_SACK:3                    	100	3
CACTUS_MINION                 	CACTUS                        	100	3
SUGAR_CANE_MINION             	SUGAR_CANE                    	100	3
COW_MINION                    	RAW_BEEF                      	100	1
COW_MINION                    	LEATHER                       	100	1
PIG_MINION                    	PORK                          	100	1
CHICKEN_MINION                	RAW_CHICKEN                   	100	1
CHICKEN_MINION                	FEATHER                       	100	1
SHEEP_MINION                  	MUTTON                        	100	1
SHEEP_MINION                  	WOOL                          	100	1
RABBIT_MINION                 	RABBIT_FOOT                   	35	1
RABBIT_MINION                 	RABBIT_HIDE                   	35	1
COBBLESTONE_MINION            	COBBLESTONE                   	100	1
COAL_MINION                   	COAL                          	100	1
IRON_MINION                   	IRON_ORE                      	100	1
GOLD_MINION                   	GOLD_ORE                      	100	1
DIAMOND_MINION                	DIAMOND                       	100	1
LAPIS_MINION                  	INK_SACK:4                    	100	6
REDSTONE_MINION               	REDSTONE                      	100	4.5
EMERALD_MINION                	EMERALD                       	100	1
QUARTZ_MINION                 	QUARTZ                        	100	1
OBSIDIAN_MINION               	OBSIDIAN                      	100	1
GRAVEL_MINION                 	GRAVEL                        	100	1
ICE_MINION                    	ICE                           	100	1
SAND_MINION                   	SAND                          	100	1
CLAY_MINION                   	CLAY                          	100	4
ZOMBIE_MINION                 	ROTTEN_FLESH                  	100	1
ZOMBIE_MINION                 	POISONOUS_POTATO              	1	2.5
SKELETON_MINION               	BONE                          	100	1
SPIDER_MINION                 	STRING                        	100	1
SPIDER_MINION                 	SPIDER_EYE                    	50	1
CAVE_SPIDER_MINION            	STRING                        	50	1
CAVE_SPIDER_MINION            	SPIDER_EYE                    	100	1
ENDERMAN_MINION               	ENDER_PEARL                   	100	1
GHAST_MINION                  	GHAST_TEAR                    	100	1
SLIME_MINION                  	SLIME_BALL                    	100	1.8
BLAZE_MINION                  	BLAZE_ROD                     	100	1
MAGMA_CUBE_MINION             	MAGMA_CREAM                   	100	1.8
REVENANT_MINION               	ROTTEN_FLESH                  	100	3
REVENANT_MINION               	DIAMOND                       	20	1
TARANTULA_MINION              	STRING                        	100	3.16
TARANTULA_MINION              	SPIDER_EYE                    	100	1
TARANTULA_MINION              	IRON_INGOT                    	20	1
OAK_MINION                    	LOG                           	100	1
SPRUCE_MINION                 	LOG:1                         	100	1
BIRCH_MINION                  	LOG:2                         	100	1
DARK_OAK_MINION               	LOG_2:1                       	100	1
ACACIA_MINION                 	LOG_2                         	100	1
JUNGLE_MINION                 	LOG:3                         	100	1
SNOW_MINION                   	SNOW_BALL                     	100	4
FISHING_MINION                	RAW_FISH                      	50	1
FISHING_MINION                	RAW_FISH:1                    	25	1
FISHING_MINION                	RAW_FISH:2                    	4	1
FISHING_MINION                	RAW_FISH:3                    	12	1
FISHING_MINION                	PRISMARINE_CRYSTALS           	3	1
FISHING_MINION                	PRISMARINE_SHARD              	3	1
FISHING_MINION                	SPONGE                        	3	1
FLOWER_MINION                 	FLOWER                        	100	1
CARROT_MINION                 	CARROT_ITEM                   	100	3
POTATO_MINION                 	POTATO_ITEM                   	100	3
RABBIT_MINION                 	RABBIT                        	100	1
NETHER_WARD_MINION            	NETHER_STALK                  	100	4
CREEPER_MINION                	SULPHUR                       	100	1
GLOWSTONE_MINION              	GLOWSTONE_DUST                	100	3
END_STONE_MINION              	ENDER_STONE                   	100	1
\.


--
-- Data for Name: upgrades; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.upgrades (name, upgrade_group) FROM stdin;
AUTO_SMELTER                  	SMELT                         
COMPACTOR                     	COMPACT                       
DIAMOND_SPREADING             	ALL                           
MINION_EXPANDER               	ALL                           
ENCHANTED_EGG                 	CHICKEN                       
FLINT_SHOVEL                  	FLINT                         
FLYCATCHER                    	ALL                           
SUPER_COMPACTOR_3000          	ALL                           
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, email, password, first_name, gender, country, nickname, facebook, vk, role) FROM stdin;
11	OneWayDream                   	onewaydream@mail.ru           	$2a$10$L26tQZDA5jKxuDyN3tKvNeLX.5XPvH33PfKbujoeIQAvhMgvGErHW                                        	Kirill                        	1	Russia                        	Sypergrom                     	facebook.com/yep              	vk.com/yep                    	Admin                         
\.


--
-- Name: cookies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cookies_id_seq', 17, true);


--
-- Name: item_bazaar_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_bazaar_price_id_seq', 145, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 15, true);


--
-- Name: cookies cookies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cookies
    ADD CONSTRAINT cookies_pkey PRIMARY KEY (id);


--
-- Name: fuels fuels_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuels
    ADD CONSTRAINT fuels_pkey PRIMARY KEY (name);


--
-- Name: item_bazaar_price item_bazaar_price_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_bazaar_price
    ADD CONSTRAINT item_bazaar_price_pkey PRIMARY KEY (id);


--
-- Name: minions minions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.minions
    ADD CONSTRAINT minions_pkey PRIMARY KEY (name);


--
-- Name: upgrades upgrades_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.upgrades
    ADD CONSTRAINT upgrades_pkey PRIMARY KEY (name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: cookies cookies_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cookies
    ADD CONSTRAINT cookies_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: minion_upgrade_group minion_upgrade_group_minion_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.minion_upgrade_group
    ADD CONSTRAINT minion_upgrade_group_minion_name_fkey FOREIGN KEY (minion_name) REFERENCES public.minions(name);


--
-- Name: minion_upgrade_group minion_upgrade_group_upgrade_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.minion_upgrade_group
    ADD CONSTRAINT minion_upgrade_group_upgrade_name_fkey FOREIGN KEY (upgrade_name) REFERENCES public.upgrades(name);


--
-- PostgreSQL database dump complete
--

