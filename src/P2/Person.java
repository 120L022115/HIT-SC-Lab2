package P2;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private static Set<String> persons = new HashSet<String>();

	private String name;	
	private Set<Person> fatherFriends = new HashSet<Person>();
	private Set<Person> childFriends = new HashSet<Person>();
	
	/**
	 * create a person
	 * @param name
	 */
	public Person(String name){
		if(persons.contains(name)) throw new IllegalArgumentException("已有名字为"+name+"的人，请更换名字后再试");
		this.name = name;
		persons.add(name);
	}
	/**
	 * get name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * get friends which $this are not accepted.
	 * @return set of persons
	 */
	public Set<Person> getFriendFather() {
		return Set.copyOf(this.fatherFriends);
	}
	/**
	 * get friends which only accepted by $this.
	 * @return set of persons
	 */
	public Set<Person> getFriendChild() {
		return Set.copyOf(this.childFriends);
	}
	/**
	 * add friends which $this are not accepted.
	 * @param p
	 */
	public void addFriendFather(Person p) {
		this.fatherFriends.add(p);
	}
	/**
	 * add friends which only accepted by $this.
	 * @param p
	 */
	public void addFriendChild(Person p) {
		this.childFriends.add(p);
	}
	/**
	 * add friends by two people accepted
	 * @param p
	 */
	public void addFriend(Person p) {
		this.fatherFriends.add(p);
		this.childFriends.add(p);
	}
	@Override
	public String toString() {
		return name;
	}
	public String toStringAll() {
		Person[] arr;
		arr = this.getFriendChild().toArray(new Person[0]);
		String childs = "",fathers="";
		if(arr.length!=0)childs=arr[0].getName();
		for(int i=1;i<arr.length;i++) {
			childs+=","+arr[i].getName();
		}
		arr = this.getFriendFather().toArray(new Person[0]);
		if(arr.length!=0)childs=arr[0].getName();
		for(int i=1;i<arr.length;i++) {
			fathers+=","+arr[i].getName();
		}
		return "{\n\tname="+this.name+"\n\tchilds="+childs+"\n\tfathers="+fathers+"\n}";
	}
}
