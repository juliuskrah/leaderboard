package com.juliuskrah.leaderboard.util;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 * 
 * @author Julius Krah
 *
 */
public class SparkUtil {

	private SparkUtil() {
	}

	private static class Helper {
		private static final ThymeleafTemplateEngine INSTANCE = new ThymeleafTemplateEngine();
		static {
			INSTANCE.addThymeleafDialect(new LayoutDialect());
		}
	}

	public static String render(ModelAndView model) {
		return Helper.INSTANCE.render(model);
	}

	public static String render(Object model, String viewName) {
		return Helper.INSTANCE.render(new ModelAndView(model, viewName));
	}
}
