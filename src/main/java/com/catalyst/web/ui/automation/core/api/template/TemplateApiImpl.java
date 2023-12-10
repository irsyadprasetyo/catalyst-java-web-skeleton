package com.catalyst.web.ui.automation.core.api.template;

import com.samskivert.mustache.Mustache;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.io.FilenameUtils;

public class TemplateApiImpl implements TemplateApi {

  private Mustache.TemplateLoader templateLoader;
  private Mustache.Compiler compiler;

  public TemplateApiImpl(Mustache.TemplateLoader templateLoader, Mustache.Compiler compiler) {
    this.templateLoader = templateLoader;
    this.compiler = compiler;
  }

  public String create(String templateName, Object templateData) {
    String fileType = this.getFileType(templateName);
    if (fileType.equalsIgnoreCase("html")) {
      return this.getFromHtml(templateName, templateData);
    } else {
      return fileType.equalsIgnoreCase("json") ? this.getFromJson(templateName, templateData)
          : null;
    }
  }

  public String createFromString(String templateString, Object templateData) {
    try {
      Reader reader = new StringReader(templateString);
      return this.compiler.compile(reader).execute(templateData);
    } catch (Exception var4) {
      var4.printStackTrace();
      return null;
    }
  }

  private String getFileType(String templateName) {
    AtomicReference<String> result = new AtomicReference("");
    Path p = this.getResourcesFolderPath();
    List<String> folderSplit = Arrays.asList(templateName.split("/"));

    for (int i = 0; i < folderSplit.size() - 1; ++i) {
      p = p.resolve((String) folderSplit.get(i));
    }

    String templateLocation = (String) folderSplit.get(folderSplit.size() - 1) + ".{html,json}";

    try {
      DirectoryStream<Path> stream = Files.newDirectoryStream(p, templateLocation);
      stream.forEach((t) -> {
        result.set(FilenameUtils.getExtension(t.toString()));
      });
    } catch (IOException var7) {
      var7.printStackTrace();
    }

    return (String) result.get();
  }

  private String getFromHtml(String templateName, Object templateData) {
    try {
      Reader reader = this.templateLoader.getTemplate(templateName);
      return this.compiler.compile(reader).execute(templateData);
    } catch (Exception var4) {
      var4.printStackTrace();
      return null;
    }
  }

  private String getFromJson(String templateName, Object templateData) {
    String templateLocation = templateName + ".json";
    Path p = this.getResourcesFolderPath();
    if (!Files.isDirectory(p, new LinkOption[0])) {
      return null;
    } else {
      StringBuilder sb = new StringBuilder();

      try {
        Files.readAllLines(p.resolve(templateLocation)).forEach(sb::append);
        Reader reader = new StringReader(sb.toString());
        return this.compiler.compile(reader).execute(templateData);
      } catch (IOException var7) {
        var7.printStackTrace();
        return null;
      }
    }
  }

  private Path getResourcesFolderPath() {
    String I = File.separator;
    String templatesFolder =
        System.getProperty("user.dir") + I + "src" + I + "test" + I + "resources" + I + "templates"
            + I;
    return Paths.get(templatesFolder);
  }
}
