import React, { useState, useEffect } from "react";
import { motion, AnimatePresence } from "motion/react";

export default function AboutWorkspace() {
  const [showIntroS, setShowIntroS] = useState<boolean>(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowIntroS(false);
    }, 1800);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className="space-y-6 text-right leading-relaxed" dir="rtl">
      
      {/* Absolute floating intro S animation for About page */}
      <AnimatePresence>
        {showIntroS && (
          <motion.div
            key="about-intro-overlay"
            className="fixed inset-0 flex items-center justify-center z-[99999] bg-white/25 backdrop-blur-xs pointer-events-none"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.3 }}
          >
            <motion.span
              layoutId="about-logo-s"
              className="text-9xl md:text-[14rem] font-black text-white font-sans tracking-tight filter drop-shadow-2xl"
              animate={{ rotate: 720 }}
              transition={{ duration: 1.5, ease: "easeOut" }}
            >
              S
            </motion.span>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Brand Card with elegant logo container */}
      <div className="bg-gradient-to-tr from-[#dd7390] to-indigo-600 text-white p-6 md:p-8 rounded-3xl shadow-lg relative overflow-hidden">
        <div className="absolute top-0 right-0 w-32 h-32 bg-white/10 rounded-full blur-2xl pointer-events-none"></div>
        <div className="flex items-center gap-4 relative z-10">
          <div>
            <h2 className="text-3xl font-black font-sans tracking-tight flex items-center gap-0.5 select-none">
              {!showIntroS ? (
                <motion.span 
                  layoutId="about-logo-s"
                  className="text-white inline-block font-black"
                  transition={{ type: "spring", stiffness: 85, damping: 14 }}
                >
                  S
                </motion.span>
              ) : (
                <span className="text-white inline-block font-black opacity-0 select-none pointer-events-none">S</span>
              )}
              <span className="text-white">titchlab</span>
            </h2>
            <p className="text-[10px] text-indigo-100 font-semibold tracking-wider uppercase mt-1 antialiased">
              مساحة تعليمية مبتكرة
            </p>
          </div>
        </div>
      </div>

      {/* Main Content Areas with exact text requested */}
      <div className="bg-slate-900 border border-slate-850 p-6 md:p-8 rounded-3xl space-y-6 shadow-sm">
        <p id="about-info-par1" className="text-sm md:text-base text-slate-200 font-bold leading-relaxed whitespace-pre-line antialiased">
          StitchLab هو مساحة تعليمية مبتكرة تساعد على تعلم الكلمات والقواعد بطريقة ممتعة وتفاعلية. لنصنع معاً مستقبلاً مليئاً بالإبداع والمعرفة.
        </p>
        
        <div className="w-16 h-[2px] bg-gradient-to-r from-[#ff758c] to-[#4f46e5] rounded-full"></div>

        <p id="about-info-par2" className="text-xs md:text-sm text-slate-400 font-medium leading-relaxed antialiased">
          محتوانا مصمم وفق معايير الجودة الأكاديمية العالمية.
        </p>
      </div>

    </div>
  );
}
