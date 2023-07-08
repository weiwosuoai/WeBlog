package com.quanxiaoha.weblog.web.utils;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.CharSubSequence;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author jiangbing(江冰)
 * @date 2018/4/6
 * @time 下午12:32
 * @discription markdown 工具类
 **/
@Slf4j
public class MarkdownUtil {

    private final static Parser parser;
    private final static HtmlRenderer renderer;

    private static final String SITE_NAME = "www.quanxiaoha.com";

    static {
        // 定制 markdown 选项
        MutableDataSet options = new MutableDataSet();

        // set optional extensions
        options.set(Parser.EXTENSIONS, Arrays.asList(
                TablesExtension.create(),
                StrikethroughExtension.create(),
                NofollowExtension.create(),
                NodeInsertingPostProcessorExtension.create()));

        // convert soft-breaks to hard breaks
//        options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }

    static class NodeInsertingPostProcessor extends NodePostProcessor {
        private static class NodeInsertingFactory extends NodePostProcessorFactory {
            NodeInsertingFactory(DataHolder options) {
                super(false);
                addNodes(Image.class);
//                addNodes(ImageRef.class);
                addNodes(Link.class);
            }

            @NotNull
            @Override
            public NodePostProcessor apply(@NotNull Document document) {
                return new NodeInsertingPostProcessor();
            }
        }

        public static NodePostProcessorFactory Factory(DataHolder options) {
            return new NodeInsertingFactory(options);
        }

        @Override
        public void process(@NotNull NodeTracker state, @NotNull Node node) {
            BasedSequence paragraphText = BasedSequence.NULL;
            if (node instanceof Image) { // ![bar](http://example.com)
                Image image = (Image) node;
                paragraphText = image.getText();

                if (!paragraphText.isBlank()) {

// create a text element to hold the text
//                    Text text = new Text(PrefixedSubSequence.prefixOf(paragraphText.toString(), paragraphParent.getChars().getEmptySuffix()));

                    // create a paragraph for the text
                    String html = String.format("<span class=\"image-caption\">%s</span>", paragraphText);
                    HtmlInline inline = new HtmlInline(CharSubSequence.of(html));


//                Paragraph paragraph = new Paragraph(text.getChars());
//
//                // this will allows us add attributes in the AST without needing to modify the attribute provider
//                MutableAttributes attributes = new MutableAttributes();
//                attributes.addValue("class", "caption");
//
//                paragraph.appendChild(new EmbeddedAttributeProvider.EmbeddedNodeAttributes(paragraph, attributes));
//                paragraph.appendChild(text);
//                paragraph.setCharsFromContent();


//                paragraphParent.insertAfter();
                    node.insertAfter(inline);
//                paragraphParent.setCharsFromContent();

                    state.nodeAdded(inline);
                }
            } else if (node instanceof  Link) {
                Node paragraphParent = node.getAncestorOfType(Paragraph.class);

                if (paragraphParent != null) {
                    String html = "<span><svg xmlns=\"http://www.w3.org/2000/svg\" style=\"color: #aaa;\" aria-hidden=\"true\" focusable=\"false\" x=\"0px\" y=\"0px\" viewBox=\"0 0 100 100\" width=\"15\" height=\"15\" class=\"icon outbound\"><path fill=\"currentColor\" d=\"M18.8,85.1h56l0,0c2.2,0,4-1.8,4-4v-32h-8v28h-48v-48h28v-8h-32l0,0c-2.2,0-4,1.8-4,4v56C14.8,83.3,16.6,85.1,18.8,85.1z\"></path> <polygon fill=\"currentColor\" points=\"45.7,48.7 51.3,54.3 77.2,28.5 77.2,37.2 85.2,37.2 85.2,14.9 62.8,14.9 62.8,22.9 71.5,22.9\"></polygon></svg> <span class=\"sr-only\"></span></span>";
                    HtmlInline inline = new HtmlInline(CharSubSequence.of(html));
                    node.insertAfter(inline);
                    state.nodeAdded(inline);
                }
            }
        }
    }

    static class NodeInsertingPostProcessorExtension implements Parser.ParserExtension {
        private NodeInsertingPostProcessorExtension() { }

        @Override
        public void parserOptions(MutableDataHolder options) { }

        @Override
        public void extend(Parser.Builder parserBuilder) {
            parserBuilder.postProcessorFactory(NodeInsertingPostProcessor.Factory(parserBuilder));
        }

        public static NodeInsertingPostProcessorExtension create() {
            return new NodeInsertingPostProcessorExtension();
        }
    }

    static class NofollowExtension implements HtmlRenderer.HtmlRendererExtension {


        @Override
        public void rendererOptions(@NotNull MutableDataHolder mutableDataHolder) {

        }

        @Override
        public void extend(final HtmlRenderer.Builder rendererBuilder, final String rendererType) {
            rendererBuilder.attributeProviderFactory(NofollowAttributeProvider.Factory());
        }

        static NofollowExtension create() {
            return new NofollowExtension();
        }
    }

    static class NofollowAttributeProvider implements AttributeProvider {


        static AttributeProviderFactory Factory() {
            return new IndependentAttributeProviderFactory() {
                @NotNull
                @Override
                public AttributeProvider apply(@NotNull LinkResolverContext context) {
                    return new NofollowAttributeProvider();
                }
            };
        }

        @Override
        public void setAttributes(@NotNull Node node,
                                  @NotNull AttributablePart attributablePart,
                                  @NotNull MutableAttributes mutableAttributes) {
            if ((node instanceof Link || node instanceof AutoLink)
                    && (attributablePart == AttributablePart.LINK)) {

                mutableAttributes.replaceValue("target", "_blank");
                String href = mutableAttributes.getValue("href");
                // 对于 md 文档中不包含本站域名的，设置 nofollow
                if (!href.contains(SITE_NAME)) {
                    // Put info in custom attribute instead
                    mutableAttributes.replaceValue("rel", "nofollow");
                }
            }
        }
    }

    /**
     * 解析 markdown 为 html
     * @param mdStr
     * @return
     */
    public static String parse2Html(String mdStr) {

        try {
            Node document = parser.parse(mdStr);
            return renderer.render(document);
        } catch (Exception e) {
            log.error("markdown parse to html exception: ", e);
            return null;
        }
    }

    public static void main(String[] args) {
//        System.out.println(parse2Html("|  fdsf |  fdsf |\n" +
//                "| ------------ | ------------ |\n" +
//                "|  fdsf |fsdf   |\n" +
//                "|fsdf   |  fdsf |\n" +
//                "\n" +
//                ":warnings:"));

        System.out.println(parse2Html("## 一、Spring Boot 是什么ss\n" +
                "\n" +
                "以下截图自 [Spring Boot 官方文档](https://spring.io/projects/spring-boot/)：\n" +
                "\n" +
                "![什么是Spring Boot](https://exception-image-bucket.oss-cn-hangzhou.aliyuncs.com/155523379091222 \"什么是Spring Boot\")"));

//        StringBuilder html = new StringBuilder("");
//        String markdown = MarkdownUtil.parse2Html(html.toString());
//
////        System.out.println("HTML:");
////        System.out.println(html);
//
//        System.out.println("\nMarkdown:");
//        System.out.println(markdown);
    }

}
