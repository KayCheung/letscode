package com.studytrails.json.jackson;

public class DestinationInfo {
	public String destId;
	public String destType;
	public String destName;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destId == null) ? 0 : destId.hashCode());
		result = prime * result
				+ ((destName == null) ? 0 : destName.hashCode());
		result = prime * result
				+ ((destType == null) ? 0 : destType.hashCode());
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
		DestinationInfo other = (DestinationInfo) obj;
		if (destId == null) {
			if (other.destId != null)
				return false;
		} else if (!destId.equals(other.destId))
			return false;
		if (destName == null) {
			if (other.destName != null)
				return false;
		} else if (!destName.equals(other.destName))
			return false;
		if (destType == null) {
			if (other.destType != null)
				return false;
		} else if (!destType.equals(other.destType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " [ " + destName + "," + destType + "," + destId + " ] ";
	}

}
