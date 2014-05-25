package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

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
	public List<BlackListDao> getAllBlackList() {
		return blackListDao.getAllBlackList();
	}

	@Override
	public void addBlackList(BlackListDao blackList) {
		blackListDao.addBlackList(blackList);
	}

	@Override
	public boolean delBlackList(int id) {
		return blackListDao.delBlackList(id);
	}

	@Override
	public boolean updateBlackList(BlackListDao blackList) {
		return blackList.updateBlackList(blackList);
	}

}