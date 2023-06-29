package live.webcrawls.midas.common.module;

import java.util.*;

public class ModuleRegistry {

    private final Map<String, MidasModule> modules;
    private final Set<String> enabledModules;

    public ModuleRegistry() {
        this.modules = new HashMap<>();
        this.enabledModules = new HashSet<>();
    }

    public void enableModule(String id) {
        if (this.modules.containsKey(id)) {
            this.enabledModules.add(id);
        }
    }

    public void disableModule(String id) {
        if (this.modules.containsKey(id)) {
            this.enabledModules.remove(id);
        }
    }

    public void registerModule(MidasModule module) {
        this.modules.put(module.id(), module);
    }

    public void unregisterModule(MidasModule module) {
        this.modules.remove(module.id(), module);
    }

    public List<MidasModule> all() {
        return List.copyOf(this.modules.values());
    }

}
