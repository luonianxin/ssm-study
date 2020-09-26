package com.learn.ssm.chapter5.pojo;

/**
 *  员工任务表对应的关联实体
 */
public class EmployeeTask {
    private Long id;
    private Long empId;
    private Task task;
    private String taskName;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "EmployeeTask{" +
                "id=" + id +
                ", empId=" + empId +
                ", task=" + task +
                ", taskName='" + taskName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
