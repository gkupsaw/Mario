package Mario;

public class Map {

	private Type[][] _map;
	private Type[][] _initOffScreen;

	public Map() {
		_map = new Type[Constants.BLOCKS_HIGH][Constants.TOT_BLOCKS_WIDE];
		_initOffScreen = new Type[Constants.BLOCKS_HIGH][Constants.OFFSET];
		this.offScreen();
		this.addFloor();
		this.addOthers();
	}

	public void offScreen() {
		for (int i = 0; i < Constants.BLOCKS_HIGH; i++) {
			for (int j = 0; j < Constants.OFFSET; j++) {
				if (i > Constants.BLOCKS_HIGH - 3) {
					_initOffScreen[i][j] = Type.FLOOR;
				} else {
					_initOffScreen[i][j] = Type.FREE;
				}
			}
		}
	}

	public void addFloor() {
		for (int i = Constants.BLOCKS_HIGH - 2; i < Constants.BLOCKS_HIGH; i++) {
			for (int j = 0; j < Constants.TOT_BLOCKS_WIDE; j++) {
				_map[i][j] = Type.FLOOR;
			}
		}
	}

	public void addOthers() {
		int loc = 0;
		for (int i = 0; i < Constants.BLOCKS_HIGH; i++) {
			for (int j = 0; j < Constants.TOT_BLOCKS_WIDE; j++) {
				loc = j + i * Constants.TOT_BLOCKS_WIDE;
				this.checkBGLoc(loc, i, j);
				this.checkBrickLoc(loc, i, j);
				this.checkPitLoc(loc, i, j);
				this.checkStairLoc(loc, i, j);
				this.checkMysteryLoc(loc, i, j);
				this.checkEnemyLoc(loc, i, j);
				this.checkPipeLoc(loc, i, j);
				if (_map[i][j] == null) {
					_map[i][j] = Type.FREE;
				}
			}
		}
	}

	public void checkBGLoc(int loc, int i, int j) {
		switch (loc) {
		case 2090:
		case 2138:
		case 2186:
		case 2234:
		case 2282:
			_map[i][j] = Type.BIGHILL;
			break;
		case 2315:
		case 2363:
		case 2412:
		case 2459:
			_map[i][j] = Type.SMALLHILL;
			break;
		case 2310:
		case 2357:
		case 2408:
			_map[i][j] = Type.BIGBUSH;
			break;
		case 2341:
		case 2388:
		case 2436:
			_map[i][j] = Type.MEDBUSH;
			break;
		case 2323:
		case 2370:
		case 2418:
		case 2456:
		case 2466:
			_map[i][j] = Type.SMALLBUSH;
			break;
		default:
			break;
		}
	}

	public void checkBrickLoc(int loc, int i, int j) {
		switch (loc) {
		case 1692:
		case 1694:
		case 1696:

		case 1749:
		case 1751:

		case 916:
		case 917:
		case 918:
		case 919:
		case 920:
		case 921:
		case 922:
		case 923:

		case 927:
		case 928:
		case 929:
		case 1766:

		case 1772:
		case 1773:

		case 1790:
		case 957:
		case 958:
		case 959:
		case 964:
		case 967:
		case 1801:
		case 1802:

		case 1840:
		case 1841:
		case 1843:
			_map[i][j] = Type.BRICK;
			break;
		default:
			break;
		}
	}

	public void checkPitLoc(int loc, int i, int j) {
		switch (loc) {
		case 2577:
		case 2578:
		case 2786:
		case 2787:

		case 2594:
		case 2595:
		case 2596:
		case 2803:
		case 2804:
		case 2805:

		case 2661:
		case 2662:
		case 2870:
		case 2871:
			_map[i][j] = Type.PIT;
			break;
		default:
			break;
		}
	}

	public void checkStairLoc(int loc, int i, int j) {
		switch (loc) {
		case 2433:
		case 2434:
		case 2435:
		case 2436:
		case 2439:
		case 2440:
		case 2441:
		case 2442:
		case 2225:
		case 2226:
		case 2227:
		case 2230:
		case 2231:
		case 2232:
		case 2017:
		case 2018:
		case 2021:
		case 2022:
		case 1809:
		case 1812:

		case 2447:
		case 2448:
		case 2449:
		case 2450:
		case 2451:
		case 2454:
		case 2455:
		case 2456:
		case 2457:
		case 2239:
		case 2240:
		case 2241:
		case 2242:
		case 2245:
		case 2246:
		case 2247:
		case 2031:
		case 2032:
		case 2033:
		case 2036:
		case 2037:
		case 1823:
		case 1824:
		case 1827:

		case 2480:
		case 2481:
		case 2482:
		case 2483:
		case 2484:
		case 2485:
		case 2486:
		case 2487:
		case 2488:

		case 2272:
		case 2273:
		case 2274:
		case 2275:
		case 2276:
		case 2277:
		case 2278:
		case 2279:

		case 2064:
		case 2065:
		case 2066:
		case 2067:
		case 2068:
		case 2069:
		case 2070:

		case 1856:
		case 1857:
		case 1858:
		case 1859:
		case 1860:
		case 1861:

		case 1648:
		case 1649:
		case 1650:
		case 1651:
		case 1652:

		case 1440:
		case 1441:
		case 1442:
		case 1443:

		case 1232:
		case 1233:
		case 1234:

		case 1024:
		case 1025:
			_map[i][j] = Type.STAIR;
			break;
		default:
			break;
		}
	}

	public boolean checkPipeLoc(int loc, int i, int j) {
		switch (loc) {
		case 2118: // tl
		case 1919:
		case 1718:
		case 1729:
		case 2253:
		case 2269:
			_map[i][j] = Type.PIPE_TL;
			break;

		case 2119: // tr
		case 1920:
		case 1719:
		case 1730:
		case 2254:
		case 2270:
			_map[i][j] = Type.PIPE_TR;
			break;

		case 2327: // l
		case 2128:
		case 2337:
		case 1927:
		case 2136:
		case 2345:
		case 1938:
		case 2147:
		case 2356:
		case 2462:
		case 2478:
			_map[i][j] = Type.PIPE_L;
			break;

		case 2328: // r
		case 2129:
		case 2338:
		case 1928:
		case 2137:
		case 2346:
		case 1939:
		case 2148:
		case 2357:
		case 2463:
		case 2479:
			_map[i][j] = Type.PIPE_R;
			break;
		default:
			break;
		}
		return false;
	}

	public void checkMysteryLoc(int loc, int i, int j) {
		switch (loc) {
		case 1688:
		case 858:
		case 1695:
		case 930:
		case 1781:
		case 1784:
		case 945:
		case 965:
		case 966:
		case 1842:
			_map[i][j] = Type.COIN_MYSTERY;
			break;
		case 1693:
			_map[i][j] = Type.MUSHROOM_MYSTERY;
			break;
		case 1750:
			_map[i][j] = Type.FLOWER_MYSTERY;
			break;
		case 1778:
			_map[i][j] = Type.STAR_MYSTERY;
			break;
		default:
			break;
		}
	}

	public void checkEnemyLoc(int loc, int i, int j) {
		switch (loc) {
		case 2319:
		case 2341:
		case 2353:
		case 2355:
		case 709:
		case 711:
		case 2396:
		case 2398:
		case 2416:
		case 2418:
		case 2424:
		case 2426:
		case 2430:
		case 2432:
		case 2472:
		case 2474:

		case 2314:
		case 2315:
			_map[i][j] = Type.GOOMBA_START;
			break;
		case 2402:
			_map[i][j] = Type.KOOPA_START;
			break;
		default:
			break;
		}
	}

	public Type[][] getMap() {
		return _map;
	}

	public Type[][] getOffScreen() {
		return _initOffScreen;
	}

}