package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        Role result = store.findById("role1");
        assertThat(result.getRoleName(), is("Engineer"));
    }

    @Test
    public void whenReplaceAndFindThenRolenameIsPilot() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        store.replace("role1", new Role("role1", "Pilot"));
        Role result = store.findById("role1");
        assertThat(result.getRoleName(), is("Pilot"));
    }

    @Test
    public void whenDeleteAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        store.delete("role1");
        Role result = store.findById("role1");
        assertNull(result);
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        Role result = store.findById("role2");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindThenRolenameIsEngineer() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        store.add(new Role("role1", "Pilot"));
        Role result = store.findById("role1");
        assertThat(result.getRoleName(), is("Engineer"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("role1", "Engineer"));
        store.replace("role2", new Role("role2", "Pilot"));
        Role result = store.findById("role1");
        assertThat(result.getRoleName(), is("Engineer"));
    }
}