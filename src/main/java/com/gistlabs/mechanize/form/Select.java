/**
 * Copyright (C) 2012 Gist Labs, LLC. (http://gistlabs.com)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gistlabs.mechanize.form;

import java.util.ArrayList;
import java.util.List;

import com.gistlabs.mechanize.html.HtmlElement;
import com.gistlabs.mechanize.query.Query;
import com.gistlabs.mechanize.query.QueryBuilder;

/**
 * @author Martin Kersten<Martin.Kersten.mk@gmail.com>
 * @version 1.0
 * @since 2012-09-12
 */
public class Select extends FormElement {

	private final boolean isMultiple;
	private final List<Option> options;
	
	public Select(Form form, HtmlElement element) {
		super(form, element);
		isMultiple = element.hasAttribute("multiple");
		options = new ArrayList<Option>();
		
		for(HtmlElement optionElement : element.getAll(QueryBuilder.byTag("option")))
			options.add(new Option(optionElement));
	}
	
	public boolean isMultiple() {
		return isMultiple;
	}
	
	/** Returns the option representing the given value or inner-HTML text. */
	public Option getOption(String valueOrText) {
		return getOption(QueryBuilder.byValue(valueOrText).or.byInnerHtml(valueOrText));
	}
	
	/** Returns the first option matching the given query or null. */
	public Option getOption(Query query) {
		for(Option option : options)
			if(query.matches(option.getElement()))
				return option;
		return null;
	}
	
	/** Returns a new list containing all options in the order of occurence. */
	public List<Option> getOptions() {
		return new ArrayList<Option>(options);
	}
	
	/** Returns a new list containing all options matching the given query. */
	public List<Option> getOptions(Query query) {
		List<Option> result = new ArrayList<Option>();
		for(Option option : options)
			if(query.matches(option.getElement()))
				result.add(option);
		return result;
	}
	
	@Override
	public void setValue(String value) {
		throw new UnsupportedOperationException("Value of hidden field may not be changed / set");
	}
	
	public class Option {

		private final HtmlElement element;
		private final String text;
		private final String value;
		
		private boolean isSelected;
		
		public Option(HtmlElement element) {
			this.element = element;
			text = element.getInnerHtml();
			value = element.hasAttribute("value") ? element.getAttribute("value") : text;
			isSelected = element.hasAttribute("selected");
		}

		public HtmlElement getElement() {
			return element;
		}
		
		public boolean isSelected() {
			return isSelected;
		}
		
		public void setSelected(boolean isSelected) {
			if(isSelected && !isMultiple()) 
				for(Option option : options)
					option.unselect();
			this.isSelected = isSelected;
		}
		
		public void select() {
			setSelected(true);
		}
		
		public void unselect() {
			setSelected(false);
		}
		
		public String getText() {
			return text;
		}
		
		public String getValue() {
			return value;
		}
	}
}
