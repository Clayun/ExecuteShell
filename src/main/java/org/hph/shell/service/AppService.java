package org.hph.shell.service;

import org.hph.shell.model.XShell;

import java.util.List;

public interface AppService {

    List<String> runShell(XShell xShell);
}
