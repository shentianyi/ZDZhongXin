package com.zd.csms.quartz.market;

import java.util.Calendar;
import java.util.List;

import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;

/**
 * 经销商缴费定时任务 查询出需要缴费的经销商 扣钱 然后加30天
 *
 */
public class DealerPayment {

	public void run() {
		DealerService service = new DealerService();
		Calendar c = Calendar.getInstance();
		int i = 0;

		while (true) {
			i++;
			List<DealerVO> list = service.findListByPaymentDate();
			if (list == null || list.size() <= 0 || i > 5) {
				break;
			}
			for (DealerVO dealer : list) {
				double money = service.getPaymentMoney(Double.parseDouble(dealer.getSuperviseMoney()),
						dealer.getPayMode());
				dealer.setBalance(dealer.getBalance() - money);
				c.setTime(dealer.getPayDate());
				c.add(Calendar.DATE, 30);// 缴费日期增加30天
				dealer.setPayDate(c.getTime());
				service.update(dealer);
			}
		}

	}
}
