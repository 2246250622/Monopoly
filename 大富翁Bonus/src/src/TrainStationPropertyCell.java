/***
 * Name: Cheuk Shu Ho
 * ID:21237387
 * Section: 1
 *
 * Disclaimer: I have not committed any form of plagiarism. I did not disclose any
 *             part of my code to my classmate. I did not upload my code to any
 *             website or public repository.
 *
 * Shall you have any problem in doing the assignment, please feel free to ask
 * questions on Piazza. However, NEVER post your code there.
 */
/**
 * If the owner owns only one train station, the rent is 500
 * If the owner owns two of them, the rent is 1000
 * If the owner owns three of them, the rent is 2000
 * If the owner owns four of them, the rent is 4000
 */
public class TrainStationPropertyCell extends PropertyCell {
    public static final int TRAIN_STATION_COST = 500;
    public static final TrainStationPropertyCell[] TRAIN_STATION_ARRAY = {
            new TrainStationPropertyCell("Kowloon"),
            new TrainStationPropertyCell("Mongkok"),
            new TrainStationPropertyCell("Central"),
            new TrainStationPropertyCell("Shatin")
    };

    public TrainStationPropertyCell(String name) {
        super(name, TRAIN_STATION_COST);
    }

    @Override
    public int getRent(Player p) {
        final int OneTrainStation = 500;
        final int TwoTrainStation = 1000;
        final int ThreeTrainStation = 2000;
        final int FourTrainStation = 4000;
        int count = 0;
        if (p == this.owner || this.owner == null) {
            return 0;
        }

        for (int i = 0; i < TRAIN_STATION_ARRAY.length; i++) {
            if (TRAIN_STATION_ARRAY[i].getOwner()==(this.getOwner())) {
                count++;
            }
        }
        switch (count) {
            case 2: {
                return TwoTrainStation;
            }
            case 3: {
                return ThreeTrainStation;
            }
            case 4: {
                return FourTrainStation;
            }
            default: {
                return OneTrainStation;
            }
        }

    }
}