package it.RGB.is.Classes;

public enum Genere {
	JAZZ, ROCK, CLASSICA, LATIN, FOLK, WORLD_MUSIC, POP;

	@Override
	public String toString() {
		switch (this) {
		case JAZZ:
			return "Jazz";
		case ROCK:
			return "Rock";
		case CLASSICA:
			return "Classica";
		case LATIN:
			return "Latin";
		case FOLK:
			return "Folk";
		case WORLD_MUSIC:
			return "World Music";
		case POP:
			return "Pop";
		default:
			return "";
		}
	}
}