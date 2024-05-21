package com.vkg.pactice.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.junit.RepositoryTestCase;
import org.junit.Test;

import java.io.IOException;

public class JgitTest extends RepositoryTestCase {
    @Test
    public void name() throws GitAPIException, IOException {
        Git git = new Git(db);
        writeTrashFile("MyFile.txt", "This is some text");
        write("Temp Contents");
        checkStatus(git);
        git.reset().setMode(ResetCommand.ResetType.HARD).call();
        git.clean().setCleanDirectories(true).setForce(true).setIgnore(true).call();
        checkStatus(git);
    }

    private void checkStatus(Git git) throws GitAPIException {
        final Status status = git.status().call();
        final boolean clean = status.isClean();

        System.out.println("**** Status ****");
        System.out.println("Clean: " + status.isClean());
        System.out.println("Untracked: " + status.hasUncommittedChanges());
        System.out.println("Conflict: " + status.getConflicting());
        System.out.println("Add: " + status.getAdded());
        System.out.println("Changed: " + status.getChanged());
        System.out.println("Removed: " + status.getRemoved());
        System.out.println("Modified: " + status.getModified());
        System.out.println("Uncommitted Changes: " + status.getUncommittedChanges());
        System.out.println("Untracked: " + status.getUntracked());
        System.out.println("Untracked Folder: " + status.getUntrackedFolders());
        System.out.println("Missing: " + status.getMissing());
    }
}
