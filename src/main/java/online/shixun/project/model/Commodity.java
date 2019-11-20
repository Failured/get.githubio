package online.shixun.project.model;

/**
 * 商品实体类
 * @author am
 *
 */
public class Commodity {

	// 编号
	private Long id;
	// 商品名称
	private String name;
	// 商品价格
	private double price = 0;
	// 商品图片路径
	private String imgPath;
	// 成交量
	private int volume = 0;
	// 库存
	private int stock = 0;
	// 商品简介
	private String introduction;
	// 点击量
	private int clickVolume = 0;
	// 商品是否有效
	private boolean effective = true;
	
	// 分类（多对一）
	private Sort sort;

	
	
	public Commodity() { }

	public Commodity(String name, double price, String imgPath, int volume, int stock, String introduction,
			int clickVolume, boolean effective, Sort sort) {
		this.name = name;
		this.price = price;
		this.imgPath = imgPath;
		this.volume = volume;
		this.stock = stock;
		this.introduction = introduction;
		this.clickVolume = clickVolume;
		this.effective = effective;
		this.sort = sort;
	}

	public Commodity(Long id, String name, double price, String imgPath, int volume, int stock, String introduction,
			int clickVolume, boolean effective, Sort sort) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgPath = imgPath;
		this.volume = volume;
		this.stock = stock;
		this.introduction = introduction;
		this.clickVolume = clickVolume;
		this.effective = effective;
		this.sort = sort;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getClickVolume() {
		return clickVolume;
	}

	public void setClickVolume(int clickVolume) {
		this.clickVolume = clickVolume;
	}

	public boolean isEffective() {
		return effective;
	}

	public void setEffective(boolean effective) {
		this.effective = effective;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
}
