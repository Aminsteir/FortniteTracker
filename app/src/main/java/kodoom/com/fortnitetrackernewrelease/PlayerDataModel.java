package kodoom.com.fortnitetrackernewrelease;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayerDataModel {

    String mPlayerName;
    String mTotalWins;
    String mTotalKills;
    String mTotalMatches;
    String mTotalWinRatio;
    String mSoloWins;
    String mSoloKills;
    String mSoloMatches;
    String mSoloWinRatio;
    String mDuoWins;
    String mDuoKills;
    String mDuoMatches;
    String mDuoWinRatio;
    String mSquadWins;
    String mSquadKills;
    String mSquadMatches;
    String mSquadWinRatio;
    
    public static PlayerDataModel fromJson (JSONObject jsonObject) {
        PlayerDataModel playerDataModel = new PlayerDataModel();
        try {
            playerDataModel.mPlayerName = jsonObject.getString("username");
            playerDataModel.mTotalWins = jsonObject.getJSONObject("totals").getString("wins");
            playerDataModel.mTotalKills = jsonObject.getJSONObject("totals").getString("kills");
            playerDataModel.mTotalMatches = jsonObject.getJSONObject("totals").getString("matchesplayed");
            playerDataModel.mTotalWinRatio = jsonObject.getJSONObject("totals").getString("winrate");
            playerDataModel.mSoloWins = jsonObject.getJSONObject("stats").getString("placetop1_solo");
            playerDataModel.mSoloKills = jsonObject.getJSONObject("stats").getString("kills_solo");
            playerDataModel.mSoloMatches = jsonObject.getJSONObject("stats").getString("matchesplayed_solo");
            playerDataModel.mSoloWinRatio = jsonObject.getJSONObject("stats").getString("winrate_solo");
            playerDataModel.mDuoWins = jsonObject.getJSONObject("stats").getString("placetop1_duo");
            playerDataModel.mDuoKills = jsonObject.getJSONObject("stats").getString("kills_duo");
            playerDataModel.mDuoMatches = jsonObject.getJSONObject("stats").getString("matchesplayed_duo");
            playerDataModel.mDuoWinRatio = jsonObject.getJSONObject("stats").getString("winrate_duo");
            playerDataModel.mSquadWins = jsonObject.getJSONObject("stats").getString("placetop1_squad");
            playerDataModel.mSquadKills = jsonObject.getJSONObject("stats").getString("kills_squad");
            playerDataModel.mSquadMatches = jsonObject.getJSONObject("stats").getString("matchesplayed_squad");
            playerDataModel.mSquadWinRatio = jsonObject.getJSONObject("stats").getString("winrate_squad");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerDataModel;
    }

    public static String fromJsonUID (JSONObject jsonObject) {
        String playerUID = "";
        try {
            playerUID = jsonObject.getString("uid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerUID;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public String getTotalWins() {
        return mTotalWins;
    }

    public String getTotalKills() {
        return mTotalKills;
    }

    public String getTotalMatches() {
        return mTotalMatches;
    }

    public String getTotalWinRatio() {
        return mTotalWinRatio;
    }

    public String getSoloWins() {
        return mSoloWins;
    }

    public String getSoloKills() {
        return mSoloKills;
    }

    public String getSoloMatches() {
        return mSoloMatches;
    }

    public String getSoloWinRatio() {
        return mSoloWinRatio;
    }

    public String getDuoWins() {
        return mDuoWins;
    }

    public String getDuoKills() {
        return mDuoKills;
    }

    public String getDuoMatches() {
        return mDuoMatches;
    }

    public String getDuoWinRatio() {
        return mDuoWinRatio;
    }

    public String getSquadWins() {
        return mSquadWins;
    }

    public String getSquadKills() {
        return mSquadKills;
    }

    public String getSquadMatches() {
        return mSquadMatches;
    }

    public String getSquadWinRatio() {
        return mSquadWinRatio;
    }
}
