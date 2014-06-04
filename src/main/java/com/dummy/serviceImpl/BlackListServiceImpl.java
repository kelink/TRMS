package com.dummy.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dummy.dao.BlackListDao;
import com.dummy.domain.BlackList;
import com.dummy.service.BlackListService;

@Service(value = "blackListService")
public class BlackListServiceImpl implements BlackListService {

	private static final Logger logger = LoggerFactory
			.getLogger(ReservationServiceImpl.class);

	@Resource(name = "blackListDao")
	private BlackListDao blackListDao;
	@Override
	public BlackList getBlackList(int id) {
		return blackListDao.getBlackList(id);
	}

	@Override
	public List<BlackList> getAllBlackList() {
		return blackListDao.getAllBlackList();
	}

	@Override
	public void addBlackList(BlackList blackList) {
		blackListDao.addBlackList(blackList);
	}

	@Override
	public boolean delBlackList(int id) {
		return blackListDao.delBlackList(id);
	}

	@Override
	public boolean updateBlackList(BlackList blackList) {
		return blackListDao.updateBlackList(blackList);
	}

	@Override
	public JSONObject getBlackListByTeam(int team_ID) {
		BlackList blackList = blackListDao.getBlackListByTeam(team_ID);
		if (blackList == null) {
			return null;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bl_ID", String.valueOf(blackList.getBl_ID()));
		map.put("reason", blackList.getReason());
		map.put("team_ID", String.valueOf(blackList.getTeam_ID()));
		return new JSONObject(map);
	}

	@Override
	public BlackList getBlackListByTeamToObject(int team_ID) {
		return blackListDao.getBlackListByTeam(team_ID);
	}
}