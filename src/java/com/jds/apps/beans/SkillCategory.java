/*
 * Created on Nov 22, 2004
 *
 * r.c.delos.santos
 * 
 */
package com.jds.apps.beans;



import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * @author r.c.delos.santos
 */

public class SkillCategory extends AbstractReferenceData {

	public String getCategoryId() {
		return getId();
	}

	public void setCategoryId(String id) {
		setId(id);
	}

    public String getCategoryName() {
        return getName();
    }

    public void setCategoryName(String name) {
        setName(name);
    }

    public String getCategoryDescription() {
        return getDescription();
    }

    public void setCategoryDescription(String desc) {
        setDescription(desc);
    }

    /**
     *  
     * @uml.property name="skillsInformation"
     * @uml.associationEnd inverse="skillCategory:accenture.manila.hr.apps.SkillsInformation" multiplicity="(0 -1)" ordering="ordered"
     */
    private List<SkillsInformation> information;

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public java.util.List<SkillsInformation> getSkillsInformation() {
        return information;
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public void setSkillsInformation(java.util.List<SkillsInformation> value) {
        information = value;
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public SkillsInformation getSkillsInformation(int i) {
        return (SkillsInformation) information.get(i);
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public Iterator<SkillsInformation> skillsInformationIterator() {
        return information.iterator();
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public boolean addSkillsInformation(SkillsInformation element) {
        return information.add(element);
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public boolean removeSkillsInformation(SkillsInformation element) {
        return information.remove(element);
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public boolean isSkillsInformationEmpty() {
        return information.isEmpty();
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public void clearSkillsInformation() {
        information.clear();
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public boolean containsSkillsInformation(SkillsInformation element) {
        return information.contains(element);
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public boolean containsAllSkillsInformation(Collection<SkillsInformation> elements) {
        return information.containsAll(elements);
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public int skillsInformationSize() {
        return information.size();
    }

    /**
     * 
     * @uml.property name="skillsInformation"
     */
    public SkillsInformation[] skillsInformationToArray() {
        return (SkillsInformation[]) information.toArray(new SkillsInformation[information
                .size()]);
    }

}