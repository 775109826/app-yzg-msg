package com.yzg.common.freemarketools;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.openhtmltopdf.swing.Java2DRenderer;
import com.openhtmltopdf.util.FSImageWriter;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/**
 * freemarker 工具类
 */
public class FreemarkeTools {

    private static final String BASE_PATH = "/templates/";
    private static final String SUFFIX = ".ftl";

    protected static Logger logger = LoggerFactory.getLogger(FreemarkeTools.class);

    private static Map<String, String> TEMPLATE_MAP = Maps.newHashMap();

    private static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = null;
        String iss = null;
        try {
            baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            iss = baos.toString();
        } catch (Exception e) {
            logger.error("字节输出流转化错误，错误原因" + e.toString());
        } finally {
            baos.flush();
            baos.close();
        }
        return iss;
    }

    /**
     * 获取模板转为html
     */
    public static String getTemplate(Map<String, Object> map, String templateName) {
        StringWriter writer = null;
        String value;
        try {
            String source = getTemplate(templateName);
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            StringTemplateLoader loader = new StringTemplateLoader();
            loader.putTemplate("", source);
            cfg.setTemplateLoader(loader);
            cfg.setDefaultEncoding("UTF-8");
            Template template = cfg.getTemplate("");
            writer = new StringWriter();
            template.setEncoding("UTF-8");
            template.setOutputEncoding("UTF-8");
            template.process(map, writer);
        } catch (Exception e) {
            logger.error("Freemarke创建模板发生失败，错误原因" + e.toString());
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                logger.error("Freemarke创建模板释放流发生失败，错误原因" + e.toString());
            } finally {
                value = writer.getBuffer().toString();
            }
        }
        return value;
    }

    public static String getTemplate(String templateName) throws IOException {
        String template = TEMPLATE_MAP.get(templateName);
        if (Strings.isNullOrEmpty(template)) {
            StringBuffer path = new StringBuffer();
            path.append(BASE_PATH).append(templateName).append(SUFFIX);
            template = inputStream2String(FreemarkeTools.class.getResourceAsStream(path.toString()));
            TEMPLATE_MAP.put(templateName, template);
        }
        return template;
    }

    /**
     * html转为图片
     *
     * @param html
     * @param inputFileName
     * @param outputFileName
     * @param widthImage
     * @param heightImage
     * @return
     * @throws IOException
     */
    public static String turnImage(String html, String inputFileName, String outputFileName, int widthImage, int heightImage) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputFileName), "UTF-8"));
            bufferedWriter.write(html);
            bufferedWriter.newLine();
        } catch (Exception e) {
            logger.error("HTML文件生成发生失败，错误原因" + e.toString());
        } finally {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                logger.error("HTML文件生成后释放流发生失败，错误原因" + e.toString());
            }
        }
        FileOutputStream fout = null;
        try {
            File f = new File(inputFileName);




            Java2DRenderer renderer = new Java2DRenderer(f, widthImage, heightImage);
            BufferedImage image = renderer.getImage();
            FSImageWriter imageWriter = new FSImageWriter();
            imageWriter.setWriteCompressionQuality(0.9f);
            File imgFile = new File(outputFileName);
            fout = new FileOutputStream(imgFile);
            imageWriter.write(image, fout);
        } catch (Exception e) {
            logger.error("HTML文件转JDG发生失败，错误原因" + e.toString());
        } finally {
            try {
                fout.close();
            } catch (IOException e) {
                logger.error("HTML文件转JDG生成后释放流发生失败，错误原因" + e.toString());
            }
        }
        return outputFileName;
    }
}
