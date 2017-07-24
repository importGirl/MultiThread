package com.wdg.base003.coll013;

/**
 * @author wangdg
 * @Description: 任务
 * @date 2017-06-11 00:50:22
 */
public class Task implements Comparable<Task> {
	private String name;
	private int id;

    public Task(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
	public int compareTo(Task task) {
		return task.id > this.id ? -1 : (this.id > task.id ? 1 : 0);
	}
}
