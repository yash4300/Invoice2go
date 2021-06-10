package com.yash.pojos;

public class Categories {

	protected int categoryId;
	protected String categoryName;
	protected String categoryDetails;

	public Categories() {

	}

	public Categories(String categoryName, String categoryDetails) {
		this.categoryName = categoryName;
		this.categoryDetails = categoryDetails;
	}

	public Categories(int categoryId, String categoryName, String categoryDetails) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDetails = categoryDetails;
	}

	@Override
	public int hashCode() {

		return categoryId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		if (categoryDetails == null) {
			if (other.categoryDetails != null)
				return false;
		} else if (!categoryDetails.equals(other.categoryDetails))
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(String categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDetails="
				+ categoryDetails + "]";
	}

}
