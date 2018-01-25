package info.stochastic.simpleShop.ejb;

import javax.ejb.Stateful;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@Stateful
@XmlRootElement
@SuppressWarnings("serial")
public class Clothes extends Product implements Serializable {
	
	public enum Color {
        NOT_DEFINED, WHITE, BLUE, RED, GREEN, BLACK
    }

    public enum ClothingType {
    	NOT_DEFINED, DRESS, TROUSERS, SKIRT, JECKET, SHIRT
    }
    
    public enum ClothingSize {
    	NOT_DEFINED, S42, S43, S44, S45, S46, S47, S48, S49, S50, S51, S52, S53, S54
    }

    private ClothingSize size;

    private Color color;

    private ClothingType type;

    private double value;

    private String description;

    public Clothes () {}

    public Clothes (ClothingSize size, Color color, ClothingType type,
    		double value, String description) {
        this.size = size;
        this.color = color;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clothes other = (Clothes) obj;
		if (color != other.color)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (size != other.size)
			return false;
		if (type != other.type)
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

}