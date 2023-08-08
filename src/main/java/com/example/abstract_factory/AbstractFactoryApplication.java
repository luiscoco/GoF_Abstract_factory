package com.example.abstract_factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AbstractFactoryApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AbstractFactoryApplication.class, args);

        IUIComponentFactory uiComponentFactory = context.getBean(IUIComponentFactory.class);

        IButton button = uiComponentFactory.createButton();
        ICheckbox checkbox = uiComponentFactory.createCheckbox();

        button.render();
        checkbox.render();
    }

    @Bean
    public IUIComponentFactory uiComponentFactory() {
        if (isWindows()) {
            return new WindowsUIComponentFactory();
        } else {
            return new MacOSUIComponentFactory();
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}

interface IButton {
    void render();
}

class WindowsButton implements IButton {
    @Override
    public void render() {
        System.out.println("Rendering a Windows button.");
    }
}

class MacOSButton implements IButton {
    @Override
    public void render() {
        System.out.println("Rendering a macOS button.");
    }
}

interface ICheckbox {
    void render();
}

class WindowsCheckbox implements ICheckbox {
    @Override
    public void render() {
        System.out.println("Rendering a Windows checkbox.");
    }
}

class MacOSCheckbox implements ICheckbox {
    @Override
    public void render() {
        System.out.println("Rendering a macOS checkbox.");
    }
}

interface IUIComponentFactory {
    IButton createButton();
    ICheckbox createCheckbox();
}

class WindowsUIComponentFactory implements IUIComponentFactory {
    @Override
    public IButton createButton() {
        return new WindowsButton();
    }

    @Override
    public ICheckbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacOSUIComponentFactory implements IUIComponentFactory {
    @Override
    public IButton createButton() {
        return new MacOSButton();
    }

    @Override
    public ICheckbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
